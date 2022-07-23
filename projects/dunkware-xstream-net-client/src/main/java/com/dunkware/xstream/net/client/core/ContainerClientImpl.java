package com.dunkware.xstream.net.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.channel.Channel;
import com.dunkware.xstream.net.client.ContainerClient;
import com.dunkware.xstream.net.client.ContainerClientEntityScanner;
import com.dunkware.xstream.net.client.ContainerClientException;

public class ContainerClientImpl implements ContainerClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void connect(String url) throws ContainerClientException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Channel getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DExecutor getExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerClientEntityScanner entityScanner(Object obj) throws ContainerClientException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
