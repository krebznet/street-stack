package com.dunkware.net.cluster.node;

import com.dunkware.spring.message.Message;

public interface ClusterNodeMessageHandler {

	// problem here is it will want to know how to reply
	public boolean nodeMessage(Message message);
}
