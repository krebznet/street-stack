package com.dunkware.trade.service.data.service.stream.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.stream.GStreamEvent;

public class DataStreamContainerWorker {

	private ClusterNode node; 
	private String workerId; 
	
	private BlockingQueue<GStreamEvent> eventQueue = new LinkedBlockingQueue<GStreamEvent>();
	
	private DKafkaByteProducer messageProducer; 
	
	
	public void start(ClusterNode node, String workerId) { 
		this.workerId = workerId; 
		this.node = node;
		// protocolMessage 
	}
	
	public void streamEvent(GStreamEvent event) { 
		eventQueue.add(event);
	}
	
	public String getWorkerId() { 
		return workerId; 
	}
	
	public ClusterNode getNode() { 
		return node; 
	}
	
	// is it a set of messages --> 
	
    
	
}