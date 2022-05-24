package com.dunkware.xstream.net.client;

import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface StreamClientHandler {
	
	public void onMessage(GNetServerMessage message);

}
