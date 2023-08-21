package com.dunkware.spring.cluster.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

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
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.pool.ObjectPool;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetBean;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.DunkNetConfig;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.core.tasks.DunkNetEventDispatcher;
import com.dunkware.spring.cluster.message.DunkNetMessage;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;
import com.dunkware.spring.cluster.protocol.DunkNetNodePing;
import com.dunkware.spring.runtime.services.ExecutorService;

@Service()
@Profile("DunkNet")
public class DunkNetImpl implements DunkNet, DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("DunkCluster");

	private Map<String, DunkNetNode> nodes = new ConcurrentHashMap<String, DunkNetNode>();

	private DunkNetNodePing descriptor;

	private DKafkaByteConsumer2 messageConsumer;

	private BlockingQueue<DunkNetMessage> messageQueue = new LinkedBlockingQueue<DunkNetMessage>();

	private DEventNode eventNode;

	private DEventTree eventTree;

	private DunkNetBean bean = new DunkNetBean();
	
	private ObjectPool<DunkNetEventDispatcher> eventDispatchers = new ObjectPool<>();
	
	private MessageRouter router;
	
	private List<DunkNetController> controllers = new ArrayList<DunkNetController>();

	@Autowired
	private List<DunkNetComponent> components;
	
	@Autowired
	private DunkNetConfig config;
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
	private ApplicationContext ac;
	
	@PostConstruct
	public void start() throws DunkNetException {
		eventTree = DEventTree.newInstance(executorService.get());
		bean.setStartTime(DDateTime.now());
		bean.setId(config.getNodeId());
		// initialize components 
		for (DunkNetComponent dunkNetComponent : components) {
			try {
				dunkNetComponent.initialize();
			} catch (Exception e) {
				logger.error(marker, "Exception Initializing DunkNet Component " + dunkNetComponent.getClass().getName() + " " +e.toString());;
				System.exit(-1);
			}
		}
		
		// build component descritpors; 
		
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
	public DunkNetServiceRequest service(Object payload) throws DunkNetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DunkNetConfig getConfig() {
		return null;
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

	

	private class MessageRouter extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					DunkNetMessage message = messageQueue.take();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		}
	}

	
	@Override
	public DExecutor getExecutor() {
		return executorService.get();
	}

	
}
