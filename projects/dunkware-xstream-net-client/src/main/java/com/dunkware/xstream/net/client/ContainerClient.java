package com.dunkware.xstream.net.client;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.channel.Channel;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public interface ContainerClient {
	
	public void connect(String url) throws ContainerClientException;
	
	public void close();
	
	public boolean isConnected();
	
	public Channel getChannel(); 
	
	public DExecutor getExecutor();
	
	ContainerClientEntityScanner entityScanner(Object obj) throws ContainerClientException;
	
}
