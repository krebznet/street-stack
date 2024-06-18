package com.dunkware.spring.cluster.core.tasks;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.message.DunkNetMessageTransport;

public class DunkNetEventDispatcher implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EventDispatcher");
	private DunkNet net; 
	private DunkNetMessageTransport transport; 
	private Object event; 
	public void set(DunkNet net, DunkNetMessageTransport transport, Object event) { 
		this.transport = transport;
		this.net = net;
		this.event = event;
		
	}
	
	public void run() { 
		if(logger.isInfoEnabled()) { 
			logger.info(marker,"Running Evenet Dispatch on Node {} with Type {}",net.getId(),transport.getPayloadClass());
		}
		Vector<DunkNetNode> nodes = net.getNodes();
		for (DunkNetNode dunkNode : nodes) {
			if(dunkNode.getDescriptor().getDescriptors().hasEvent(event)) {
				try {
					if(logger.isInfoEnabled()) { 
						logger.info("marker,Dipatching Event Payload from Node {} to {} of type {}",net.getId(),dunkNode.getId(),transport.getPayloadClass());
					}
					try {
						dunkNode.message(transport);
						
					} catch (Exception e) {
						logger.error(marker, "Exception sending event dispatcher message");
					}
						
				} catch (Exception e) {
					logger.error(marker, "Exception sending event to node " + e.toString(),e);
					//
				}
				
			}
			
		}
		
		
		
	}
}
