package com.dunkware.net.cluster.node;

import com.dunkware.spring.message.Message;

public interface ClusterMessageHandler {
	
	public default boolean clusterMessage(Message message) { 
		return false;
	}
	
	public default boolean nodeMessage(Message message) { 
		// in here we want to possibly send a message
		// back to the 
		return false;
	}

}
