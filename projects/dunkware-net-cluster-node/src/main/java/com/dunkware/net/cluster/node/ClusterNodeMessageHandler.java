package com.dunkware.net.cluster.node;

import com.dunkware.spring.messaging.message.DunkNetMessage;

public interface ClusterNodeMessageHandler {

	// problem here is it will want to know how to reply
	public boolean nodeMessage(DunkNetMessage message);
}
