package com.dunkware.xstream.net.server;

import java.util.concurrent.BlockingQueue;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface StreamConnector {

	public String getIdentifier();
	
	public BlockingQueue<GNetClientMessage> getMessageQueue();
	
	public void sendMessage(GNetServerMessage message);
	
	public boolean isConnected();
}
