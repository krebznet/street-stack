package com.dunkware.xstream.net.client;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;

public interface StreamClient {
	
	public void connect(StreamClientInput input) throws StreamClientException;
	
	public void close();
	
	public boolean isConnected();
	
	public StreamEntityScanner entityScanner(GEntityMatcher entityMatcher, String identifier) throws StreamClientException;
	
	public DExecutor getExecutor();
	
	public StreamClientConnector getConnector();
	
	public void sendMessage(GNetClientMessage message) throws StreamClientException;
	
	public void addMessageHandler(StreamClientMessageHandler handler);
	
	public void removeMessageHandler(StreamClientMessageHandler handler);
	
	
}
