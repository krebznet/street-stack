package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.concurrent.BlockingQueue;

import com.dunkware.net.proto.netstream.GNetClientConnectRequest;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface SessionContainerConnector {

	public BlockingQueue<GNetClientMessage> getMessageQueue();
	
	public GNetClientConnectRequest getHandshake();
	
	public void sendMessage(GNetServerMessage message) throws RuntimeException;
	
	public boolean isConnected();
	
	public void close();
	
	public String getIdentifier();
	
	
}
