package com.dunkware.spring.cluster.core.tasks;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.pool.ObjectPool;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;

public class DunkNetEventDispatcher implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DunkNet net; 
	private DunkNetMessageTransport transport; 
	private ObjectPool<DunkNetEventDispatcher> pool;
	public void set(DunkNet net, DunkNetMessageTransport transport, ObjectPool<DunkNetEventDispatcher> pool) { 
		this.net = net; 
		this.transport = transport;
		this.pool = pool;
	}
	
	public void run() { 
		Vector<DunkNetNode> nodes = net.getNodes();
		for (DunkNetNode dunkNode : nodes) {
			if(dunkNode.isEventConsumer(transport.getPayload())) {
				try {
					dunkNode.event(transport);	
				} catch (Exception e) {
					logger.error("Exception sending event to node " + dunkNode.getId() + " " + e.toString());
				}
				
			}
			pool.put(this);
		}
		
		
		
	}
}
