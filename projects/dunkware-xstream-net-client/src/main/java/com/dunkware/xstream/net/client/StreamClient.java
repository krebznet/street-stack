package com.dunkware.xstream.net.client;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.stream.GEntityMatcher;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;

public interface StreamClient {
	
	public void connect(StreamClientInput input) throws StreamClientException;
	
	public void close();
	
	public boolean isConnected();
	
	public StreamEntityScanner entityScanner(GEntityMatcher entityMatcher, int updateInterval, String retVars) throws StreamClientException;
	
	public DExecutor getExecutor();
	
	public StreamClientConnector getConnector();
	
	public void sendMessage(GNetClientMessage message) throws StreamClientException;
	
	public void addMessageHandler(StreamClientHandler handler);
	
	public void removeMessageHandler(StreamClientHandler handler);
	
	public StreamClientEntitySearch entitySearch(GEntityMatcher matcher, String retVars, StreamClientEntitySearchCallBack searchObserver) throws StreamClientException;
	
	public void execute(Runnable runnable);
}
