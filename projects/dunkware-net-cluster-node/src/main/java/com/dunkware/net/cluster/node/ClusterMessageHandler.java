package com.dunkware.net.cluster.node;

import com.dunkware.spring.messaging.message.DunkNetMessage;

public interface ClusterMessageHandler {
	
	public default boolean clusterMessage(DunkNetMessage message) { 
		return false;
	}
	
	public default boolean nodeMessage(DunkNetMessage message) { 
		// in here we want to possibly send a message
		// back to the 
		return false;
	}

}
