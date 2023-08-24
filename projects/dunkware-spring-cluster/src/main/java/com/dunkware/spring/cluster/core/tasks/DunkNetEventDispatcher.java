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
	private Object event; 
	public void set(DunkNet net, DunkNetMessageTransport transport, Object event) { 
		this.transport = transport;
		this.net = net;
		this.event = event;
		
	}
	
	public void run() { 
		Vector<DunkNetNode> nodes = net.getNodes();
		for (DunkNetNode dunkNode : nodes) {
			if(dunkNode.getDescriptor().getDescriptors().hasEvent(event)) {
				try {
					dunkNode.message(transport);	
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
			
		}
		
		
		
	}
}
