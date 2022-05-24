package com.dunkware.xstream.net.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientEntitySearch;
import com.dunkware.xstream.net.client.StreamClientEntitySearchObserver;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.StreamClientHandler;
import com.dunkware.xstream.net.client.StreamClientInput;
import com.dunkware.xstream.net.client.core.actions.StreamClientEntitySearchImpl;
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
			connector = StreamClientRegistry.get().createConnector(input.getConnectorType());
		} catch (Exception e) {
			throw new StreamClientException("Connector Type no bueno " + e.toString());
		}
		connector.connect(input.getConnectorType());

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
	public void addMessageHandler(StreamClientHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMessageHandler(StreamClientHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public StreamEntityScanner entityScanner(GNetEntityMatcher entityMatcher, int updateInterval, String retVars)
			throws StreamClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamClientEntitySearch entitySearch(GNetEntityMatcher matcher, String retVars,
			StreamClientEntitySearchObserver searchObserver) throws StreamClientException {
		StreamClientEntitySearchImpl search = new StreamClientEntitySearchImpl();
		search.init(matcher, retVars, searchObserver, this);
		return search;
	}

}
