package com.dunkware.xstream.net.server;

import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface StreamConnectionChannel {

	void sendMessage(GNetServerMessage message);
	
	// setQueue for forwarding inbound messages; 
}
