package com.dunkware.net.cluster.node.internal;

import com.dunkware.spring.messaging.message.DunkNetMessageTransport;

public interface TransportHandler {

	
	
	boolean handler(DunkNetMessageTransport transpor);
}
