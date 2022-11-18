package com.dunkware.net.cluster.node.internal;

import com.dunkware.spring.message.MessageTransport;

public interface TransportHandler {

	
	
	boolean handler(MessageTransport transpor);
}
