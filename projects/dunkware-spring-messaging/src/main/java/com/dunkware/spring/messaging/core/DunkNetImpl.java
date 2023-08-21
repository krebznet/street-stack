package com.dunkware.spring.messaging.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.client.RequestCallback;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.pool.ObjectPool;
import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.DunkNetBean;
import com.dunkware.spring.messaging.DunkNetException;
import com.dunkware.spring.messaging.DunkNetNode;
import com.dunkware.spring.messaging.DunkNetConfig;
import com.dunkware.spring.messaging.core.component.DunkNetComponent;
import com.dunkware.spring.messaging.core.component.DunkNetComponents;
import com.dunkware.spring.messaging.core.request.DunkNetChannelRequest;
import com.dunkware.spring.messaging.core.tasks.DunkNetEventDispatcher;
import com.dunkware.spring.messaging.event.EDunkNodeNodeOnline;
import com.dunkware.spring.messaging.message.DunkNetMessage;
import com.dunkware.spring.messaging.message.DunkNetMessageTransport;
import com.dunkware.spring.messaging.protocol.DunkNetNodePing;
import com.dunkware.spring.messaging.protocol.DunkNetNodeService;

public class DunkNetImpl implements DunkNet, DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("DunkCluster");

	private Map<String, DunkNetNode> nodes = new ConcurrentHashMap<String, DunkNetNode>();

	private DunkNetNodePing descriptor;

	private DExecutor execuor;

	private DKafkaByteConsumer2 messageConsumer;

	private BlockingQueue<DunkNetMessage> messageQueue = new LinkedBlockingQueue<DunkNetMessage>();

	private DunkNetConfig input;

	private DEventNode eventNode;

	private DEventTree eventTree;

	private DunkNetBean bean = new DunkNetBean();
	
	private ObjectPool<DunkNetEventDispatcher> eventDispatchers = new ObjectPool<>();
	
	private MessageRouter router;
	
	private List<DunkNetController> extensions = new ArrayList<DunkNetController>();

	private DunkNetComponents components;
	
	@Override
	public void start(DunkNetConfig input) throws DunkNetException {
		this.input = input;
		eventTree = DEventTree.newInstance(input.getExecutor());
		bean.setStartTime(DDateTime.now());
		bean.setId(input.getNodeId());
		
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public String getId() {
		return input.getNodeId();
	}

	
	@Override
	public void addNode(DunkNetNode node) throws DunkNetException {
		if(nodes.containsKey(node.getId())) { 
			logger.error(marker,"Adding node id " + node.getId() + " but already exists");
		}
		nodes.put(node.getId(), node);
		eventNode.event(new EDunkNodeNodeOnline(node));
	}
	@Override
	public RequestCallback service(Object payload) throws DunkNetException {
		for (DunkNetNode node : nodes.values()) {
			if(node.isServiceProvider(payload)) { 
				DunkNetNodeService descriptor = node.getServiceDescriptor(payload);
				// send a message to the node and return the request callback
				
			}
		}
		// TODO Auto-generated method stub
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
	public List<DunkNetComponent> getComponents() {
		// TODO Auto-generated method stub
		return null;
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
	public void stop() {
		// TODO Auto-generated method stub

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

	/**
	 * We broadcast our node descriptor every second or however long
	 * 
	 * @author duncankrebs
	 *
	 */
	private class PingController extends Thread {

		public void run() {
			while (!interrupted()) {
				// update a node
				// or add a node
			}
		}

	}

	@Override
	public DunkNetConfig getProperties() {
		return input;
	}

	@Override
	public DExecutor getExecutor() {
		return input.getExecutor();
	}

	// Consumer ---
	// consuming messages ->
	// channel request
	// request
	// message
	// we have pending DunkFuture.setResponse

	// No Producer -> that is on the other node

}
