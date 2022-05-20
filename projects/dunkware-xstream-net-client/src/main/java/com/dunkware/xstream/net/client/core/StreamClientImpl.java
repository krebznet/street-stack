package com.dunkware.xstream.net.client.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.data.cluster.GContainerEntity;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntityScannerStartRequest;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.StreamClientInput;
import com.dunkware.xstream.net.client.StreamClientMessageHandler;
import com.dunkware.xstream.net.client.core.registry.StreamClientRegistry;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;

public class StreamClientImpl implements StreamClient {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private StreamClientInput input; 
	private StreamClientConnector connector; 
	
	@Override
	public void connect(StreamClientInput input) throws StreamClientException {
		this.input = input;
		try {
		//	connector = StreamClientRegistry.get().createConnector(null)
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	public StreamEntityScanner entityScanner(GEntityMatcher entityMatcher, String identifier)
			throws StreamClientException {
		GNetEntityScannerStartRequest.Builder builder = GNetEntityScannerStartRequest.newBuilder();
		builder.setScannerIdent(identifier);
		builder.setMatcher(entityMatcher);
		GNetEntityScannerStartRequest req = builder.build();
		//connector.sendMessage(req);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DExecutor getExecutor() {
		return input.getExecutor();
	}

	@Override
	public StreamClientConnector getConnector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(GNetClientMessage message) throws StreamClientException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMessageHandler(StreamClientMessageHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMessageHandler(StreamClientMessageHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GContainerEntity> entitySearch(GEntityMatcher matcher) throws StreamClientException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	
	
}
