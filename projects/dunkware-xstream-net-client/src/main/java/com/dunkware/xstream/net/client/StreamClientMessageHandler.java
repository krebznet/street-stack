package com.dunkware.xstream.net.client;

import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface StreamClientMessageHandler {
	
	public void onMessage(GNetServerMessage message);

}
