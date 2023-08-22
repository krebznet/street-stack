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
import com.dunkware.common.util.pool.ObjectPool;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetBean;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.DunkNetComponent.ComponentMethod;
import com.dunkware.spring.cluster.DunkNetConfig;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.controllers.DunkNetChannelController;
import com.dunkware.spring.cluster.core.controllers.DunkNetEventController;
import com.dunkware.spring.cluster.core.controllers.DunkNetPingController;
import com.dunkware.spring.cluster.core.controllers.DunkNetServiceController;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.core.tasks.DunkNetEventDispatcher;
import com.dunkware.spring.cluster.event.EDunkNodeNodeOnline;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.DunkNetNodeChannel;
import com.dunkware.spring.cluster.protocol.DunkNetNodeDescriptor;
import com.dunkware.spring.cluster.protocol.DunkNetNodeEvent;
import com.dunkware.spring.cluster.protocol.DunkNetNodePing;
import com.dunkware.spring.cluster.protocol.DunkNetNodeService;
import com.dunkware.spring.runtime.services.ExecutorService;

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
	
	private ObjectPool<DunkNetEventDispatcher> eventDispatchers = new ObjectPool<>();
	
	private MessageRouter router;
	
	
	@Autowired(required = false)
	private List<DunkNetComponent> components;
	
	@Autowired
	private DunkNetConfig config;
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
	private ApplicationContext ac;
	
	private DunkNetChannelController channelController; 
	private DunkNetEventController eventController;
	private DunkNetServiceController serviceController;
	private DunkNetPingController pingController;
	
	
	
	@PostConstruct
	public void start() throws DunkNetException {
		if(components == null) { 
			components = new ArrayList<DunkNetComponent>();
		}
		eventTree = DEventTree.newInstance(executorService.get());
		eventNode = eventTree.getRoot().createChild(this);
		bean.setStartTime(DDateTime.now());
		bean.setId(config.getNodeId());
		// initialize components 
		descriptor = new DunkNetNodeDescriptor();
		for (DunkNetComponent dunkNetComponent : components) {
			try {
				dunkNetComponent.initialize();
				for (ComponentMethod method : dunkNetComponent.getServices()) {
					DunkNetNodeService ser = new DunkNetNodeService();
					ser.setInput(method.getParamType().getName());
					ser.setOutput(method.getReturnType().getName());
					ser.setNodeId(config.getNodeId());
					descriptor.getServices().add(ser);
				}
				for (ComponentMethod method : dunkNetComponent.getEvents()) {
					DunkNetNodeEvent ser = new DunkNetNodeEvent();
					ser.setInput(method.getParamType().getName());
					ser.setNodeId(config.getNodeId());
					descriptor.getEvents().add(ser);
				}
				for (ComponentMethod method : dunkNetComponent.getChannels()) {
					DunkNetNodeChannel ser = new DunkNetNodeChannel();
					ser.setInput(method.getParamType().getName());
					ser.setOutput(method.getReturnType().getName());
					ser.setNodeId(config.getNodeId());
					descriptor.getChannels().add(ser);
				}
				
			} catch (Exception e) {
				logger.error(marker, "Exception Initializing DunkNet Component " + dunkNetComponent.getClass().getName() + " " +e.toString());;
				System.exit(-1);
			}
		}
			
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
	
		
		// create/init controllers
		serviceController = new DunkNetServiceController();
		ac.getAutowireCapableBeanFactory().autowireBean(serviceController);
		serviceController.init(this);
		eventController = new DunkNetEventController();
		ac.getAutowireCapableBeanFactory().autowireBean(eventController);
		eventController.init(this);
		channelController = new DunkNetChannelController();
		ac.getAutowireCapableBeanFactory().autowireBean(channelController);
		channelController.init(this);
		pingController = new DunkNetPingController();
		ac.getAutowireCapableBeanFactory().autowireBean(pingController);
		pingController.init(this);
		
		messageRouter = new MessageRouter();
		messageRouter.start();
		
		
	}
	
	public void consumePing(DunkNetNodePing ping) { 
		if(ping.getId().equals(getId())) { 
			return;
		}
		DunkNetNode node = nodes.get(ping.getId());
		if(node == null) { 
			try {
				node = new DunkNodeImpl(this, ping);	
				nodes.put(ping.getId(), node);
				EDunkNodeNodeOnline event = new EDunkNodeNodeOnline(node);
				eventNode.event(event);
			} catch (Exception e) {
				logger.error(marker, "Exceptions starting new node " + e.toString());
			}
		} else { 
			node.ping(ping);
		}
	}
	
	public void sendPing(DunkNetNodePing ping) { 
		try {
			messageProducer.sendBytes(DJson.serialize(ping).getBytes());
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
		return serviceController.serviceRequest(payload);
	}

	@Override
	public DunkNetConfig getConfig() {
		return config;
	}

	@Override
	public DunkNetChannelRequest channel(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DunkNetBean getBean() {
		return bean;
	}
	
	
	
	@Override
	public Vector<DunkNetNode> getNodes(String... profiles) {
		Vector<DunkNetNode> vect = new Vector<DunkNetNode>();
		for (DunkNetNode dunkNode : nodes.values()) {
			boolean match = false;
			for (String profile : profiles) {
				if (dunkNode.hasProfile(profile) == false) {
					break;
				}
			}
			if (match) {
				vect.add(dunkNode);
			}
		}
		return vect;
	}

	@Override
	public boolean nodeExists(String id) {
		if (nodes.containsKey(id) == false) {
			return false;
		}
		return true;
	}


	@Override
	public void event(Object payload)  {
		try {
			DunkNetMessageTransport transport = DunkNetMessage.builder().event(payload).buildTransport(getId());
			DunkNetEventDispatcher dispatch = eventDispatchers.get();
			dispatch.set(this, transport, eventDispatchers);
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

	
	
	@Override
	public ComponentMethod getSerivceMethod(Object input) throws DunkNetException {
		for (DunkNetComponent dunkNetComponent : components) {
			for (ComponentMethod method : dunkNetComponent.getServices()) {
				if(method.getParamType().isInstance(input)) { 
					return method;
				}
			}
		}
		throw new DunkNetException("Service component method not found for " + input.getClass().getName());
	}

	@Override
	public ComponentMethod getChannelMethod(Object input) throws DunkNetException {
		for (DunkNetComponent dunkNetComponent : components) {
			for (ComponentMethod method : dunkNetComponent.getChannels()) {
				if(method.getParamType().isInstance(input)) { 
					return method;
				}
			}
		}
		throw new DunkNetException("Channel component method not found for " + input.getClass().getName());
	}

	@Override
	public List<ComponentMethod> getEventMethods(Object input) throws DunkNetException {
		List<ComponentMethod> results = new ArrayList<DunkNetComponent.ComponentMethod>();
		for (DunkNetComponent dunkNetComponent : components) {
			for (ComponentMethod method : dunkNetComponent.getEvents()) {
				if(method.getParamType().isInstance(input)) { 
					results.add(method);
				}
			}
		}
		return results;
		
	}





	private class MessageRouter extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					DunkNetMessage message = messageQueue.take();
			//		logger.info("recieve message " + message.getType());
					if(!pingController.handleMessage(message)) { 
						if(!eventController.handleMessage(message)) {  
							if(!serviceController.handleMessage(message)) {
								if(!channelController.handleMessage(message)) {
									logger.error("DunkNet Message Not handled " + message.getType() + " " + message.getPayload().getClass().getName() + " " + message.getHeaders().toString());
								}
							}
						}
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

	
}
