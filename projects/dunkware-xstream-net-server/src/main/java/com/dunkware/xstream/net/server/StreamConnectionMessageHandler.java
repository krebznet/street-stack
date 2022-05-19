package com.dunkware.xstream.net.server;

import com.dunkware.net.proto.netstream.GNetClientMessage;

public interface StreamConnectionMessageHandler {
	
	public void onMessage(GNetClientMessage message);

}
