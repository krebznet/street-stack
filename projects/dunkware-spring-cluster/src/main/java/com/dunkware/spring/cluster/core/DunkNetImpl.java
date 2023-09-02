package com.dunkware.spring.cluster.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetBean;
import com.dunkware.spring.cluster.DunkNetConfig;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetExtensions;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.DunkNetServiceResponse;
import com.dunkware.spring.cluster.core.controllers.DunkNetController;
import com.dunkware.spring.cluster.core.controllers.DunkNetPingPublisher;
import com.dunkware.spring.cluster.core.controllers.DunkNetState;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.core.tasks.DunkNetEventDispatcher;
import com.dunkware.spring.cluster.event.EDunkNodeNodeOnline;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.descriptors.DunkNetNodeDescriptor;
import com.dunkware.spring.runtime.services.ExecutorService;

import io.vertx.core.Future;
import io.vertx.core.Promise;

@Service()
@Profile("DunkNet")
public class DunkNetImpl implements DunkNet, DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("DunkNet");

	private Map<String, DunkNetNode> nodes = new ConcurrentHashMap<String, DunkNetNode>();

	private DunkNetNodeDescriptor descriptor;

	private DKafkaByteConsumer2 messageConsumer;
	private DKafkaByteProducer messageProducer;
	private MessageRouter messageRouter; 

	private BlockingQueue<DunkNetMessage> messageQueue = new LinkedBlockingQueue<DunkNetMessage>();

	private DEventNode eventNode;

	private DEventTree eventTree;

	private DunkNetBean bean = new DunkNetBean();
	
	private DunkNetState state = new DunkNetState();
	
	private DunkNetExtensions extensions;
	
	@Autowired
	private DunkNetConfig config;
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
	private ApplicationContext ac;
	
	
	private DunkNetController controller;
	
	private List<MessageRouter> messageRouters = new ArrayList<MessageRouter>();
	
	@PostConstruct
	public void start() throws DunkNetException {
		
		controller = new DunkNetController();
		ac.getAutowireCapableBeanFactory().autowireBean(controller);
		extensions = DunkNetExtensions.buildComponentExtensions(ac);
		controller.init(this);
		eventTree = DEventTree.newInstance(executorService.get());
		eventNode = eventTree.getRoot().createChild(this);
		bean.setStartTime(DDateTime.now());
		bean.setId(config.getNodeId());
		// initialize components 
		descriptor = new DunkNetNodeDescriptor();
			
			try {
				String pingTopic = "dunknet." + getConfig().getClusterId() + ".node.ping";
				messageProducer = DKafkaByteProducer.newInstance(config.getServerBrokers(),pingTopic,getId());
				
			} catch (Exception e) {
				logger.error("Exception creating ping kafka producer " + e.toString());
				System.exit(-1);
			}
			
			try {
				String nodeTopic = "dunknet." + getConfig().getClusterId() + ".node." + getId();
				System.out.println(nodeTopic);
				DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(config.getServerBrokers()).setClientAndGroup(getId() +  DUUID.randomUUID(5) ,getId() +  DUUID.randomUUID(5)).addTopic(nodeTopic).build();
				messageConsumer = DKafkaByteConsumer2.newInstance(spec);
				messageConsumer.addStreamHandler(this);
				messageConsumer.start();
			} catch (Exception e) {
				logger.error("Exception starting dunk net topic consumer " + e.toString());
				System.exit(-1);
			}
	
			
		DunkNetPingPublisher publisher = new DunkNetPingPublisher();
		ac.getAutowireCapableBeanFactory().autowireBean(publisher);
		publisher.init(this);
		
		int routers = 0;
		while(routers < 3) { 
			MessageRouter router = new MessageRouter();
			router.start();
			messageRouters.add(router);
			routers++;
		}
		
		
	}
	
	


	@Override
	public int getNodeCount() {
		return nodes.values().size();
	}




	@Override
	public DunkNetExtensions extensions() {
		return extensions;
	}

	
	
	@Override
	public void extension(Object extension) throws DunkNetException {
		extensions.addExtension(extension);
	}


	@Override
	public DunkNetState getState() {
		return state;
	}



	public void nodeDescriptor(DunkNetNodeDescriptor descriptor) { 
		if(descriptor.getId().equals(getId())) { 
			return;
		}
		if(logger.isTraceEnabled()) { 
			logger.trace(marker, "Node Ping ID {} Profiles {}",descriptor.getId(),descriptor.getProfiles().toString());
		}
		DunkNetNode node = nodes.get(descriptor.getId());
		if(node == null) { 
			try {
				if(logger.isDebugEnabled()) { 
					logger.debug(marker, "Adding Node {}", descriptor.getId());
				}
				node = new DunkNetNodeImpl(this, descriptor);	
				nodes.put(descriptor.getId(), node);
				EDunkNodeNodeOnline event = new EDunkNodeNodeOnline(node);
				if(logger.isDebugEnabled()) { 
					logger.debug(marker, "NodeID:" + getId() + " Added New Node " + descriptor.getId());
				}
				eventNode.event(event);
			} catch (Exception e) {
				logger.error(marker, "Exceptions starting new node " + e.toString());
			}
		} else { 
			node.nodeUpdate(descriptor);
		}
	}
	
	public void sendUpdate(DunkNetNodeDescriptor descriptor) { 
		try {
			messageProducer.sendBytes(DJson.serialize(descriptor).getBytes());
		} catch (Exception e) {
			logger.error(marker, "Exception sending ping " + e.toString());
		}
	}

	@Override
	public DunkNetNodeDescriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public String getId() {
		return config.getNodeId();
	}
	
	

	@Override
	public Object serviceBlocking(Object payload) throws DunkNetException {
		DunkNetServiceRequest sReq = service(payload);
		boolean returned = false;
		try {
			 returned = sReq.await(60, TimeUnit.SECONDS);	
		} catch (Exception e) {
			throw new DunkNetException("Interrupted Exception");
		}
		if(!returned) { 
			throw new DunkNetException("Service Request Timeout 60 Seconds");
		}
		if(sReq.isSuccess()) {
			return sReq.getResponse();
		}
		throw new DunkNetException("Error Returned: " + sReq.getError());
	}

	@Override
	public DunkNetServiceRequest service(Object payload) throws DunkNetException {
		DunkNetNode serviceNode = null;
		for (DunkNetNode node : nodes.values()) {
			if(node.getDescriptor().getDescriptors().hasService(payload)) { 
				serviceNode = node;
				break;
			}
		}
		if(serviceNode == null) { 
			throw new DunkNetException("No handler found on any nodes for input " + payload.getClass().getName());
		}
		
		DunkNetServiceRequest req = getController().nodeServiceRequest(serviceNode, payload);
		return req;
	}
	
	
	

	@Override
	public Future<Object> serviceFuure(Object payload)  {
		Promise<Object> promise = Promise.promise();
		Future<Object> future = promise.future();
		try {
			DunkNetServiceRequest req = service(payload);
			req.setPromise(promise);
			return future;
		} catch (Exception e) {
			promise.fail("Exception invoking service " + e.toString());
			return future;

		}
	}


	@Override
	public DunkNetController getController() {
		return controller;
	}

	@Override
	public DunkNetConfig getConfig() {
		return config;
	}


	@Override
	public DunkNetBean getBean() {
		return bean;
	}
	
	
	@Override
	public boolean nodeExists(String id) {
		if (nodes.containsKey(id) == false) {
			return false;
		}
		return true;
	}


	@Override
	public Vector<DunkNetNode> getNodes(String... profiles) {
		Vector<DunkNetNode> vect = new Vector<DunkNetNode>();
		for (DunkNetNode dunkNode : nodes.values()) {
			if(dunkNode.getDescriptor().profilesExists(profiles)) {
				vect.add(dunkNode);
			}
		}
		return vect;
	}



	@Override
	public void event(Object payload)  {
		try {
			DunkNetMessageTransport transport = DunkNetMessage.builder().event(payload).buildTransport(getId());
			DunkNetEventDispatcher dispatch = new DunkNetEventDispatcher();
			dispatch.set(this, transport,payload);
			getExecutor().execute(dispatch);
		} catch (Exception e) {
			logger.error(marker, "Exception sending cluster event " + e.toString());
		}
	}

	@Override
	public Vector<DunkNetNode> getNodes() {
		Vector<DunkNetNode> vect = new Vector<DunkNetNode>();
		for (DunkNetNode dunkNode : nodes.values()) {
			vect.add(dunkNode);
		}
		return vect;
	}

	@Override
	public DunkNetNode getNode(String id) {
		return nodes.get(id);
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			DunkNetMessageTransport transport = DJson.getObjectMapper().readValue(record.value(),
					DunkNetMessageTransport.class);
			DunkNetMessage message = DunkNetMessage.transport(transport);
			messageQueue.add(message);
			bean.incrementMessageCount();
		} catch (Exception e) {
			logger.error(marker, "Exception Parsing Incoming Message " + e.toString());
		}
	}

	private class MessageRouter extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					setName("DunkNet-Handler");
					DunkNetMessage message = messageQueue.take();
					try {
						if(!controller.handleInboundMessage(message)) { 
							logger.error(marker, "Unhandled Message type " + message.getType());
							continue;
						}
					} catch (DunkNetException e) {
							logger.error(marker, "Unhandled controlelr dunk net exception " + e.toString());
					}
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error(marker, "Excepton Routing Message " + e.toString());
				}
				
				
			}
		}
	}

	
	@Override
	public DExecutor getExecutor() {
		return executorService.get();
	}



	@Override
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException {
		// so this means first node that has this right.
		DunkNetNode channelNode = null;
		for (DunkNetNode node : nodes.values()) {
			if(node.getDescriptor().getDescriptors().hasChannel(payload)) { 
				channelNode = node;
				break;
			}
		}
		if(channelNode == null) { 
			throw new DunkNetException("No channel handler found on any nodes for input " + payload.getClass().getName());
		}
		return controller.nodeChannelRequest(channelNode, payload);
		
		
	}

	
}
