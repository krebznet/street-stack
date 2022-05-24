package com.dunkware.xstream.net.client.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.netstream.GNetClientConnectResponse;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientEntitySearch;
import com.dunkware.xstream.net.client.StreamClientEntitySearchCallBack;
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
	
	private List<StreamClientHandler> handlers = new ArrayList<StreamClientHandler>();
	private Semaphore handlerLock = new Semaphore(1);
	
	private ServerMessageRouter messageRouter;

	@Override
	public void connect(StreamClientInput input) throws StreamClientException {
		this.input = input;
		try {
			connector = StreamClientRegistry.get().createConnector(input.getConnectorType());
		} catch (Exception e) {
			throw new StreamClientException("Connector Type no bueno " + e.toString());
		}
		connector.connect(input.getConnectorType(), input.getClientIdentifier(), input.getStream());
		try {
			GNetClientConnectResponse response = connector.getConnectResponse();
			if(response.getConnected() == false) { 
				throw new StreamClientException("Server Returned invalid connect request err is " + response.getError());
			}
		} catch (StreamClientException e) {
			throw e;
		}
		messageRouter = new ServerMessageRouter();
		messageRouter.start();

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
		return connector;
	}

	@Override
	public void sendMessage(GNetClientMessage message) throws StreamClientException {
		connector.sendMessage(message);
	}

	@Override
	public void addMessageHandler(final StreamClientHandler handler) {
		Runnable adder = new Runnable() {
			
			@Override
			public void run() {
				try {
					handlerLock.acquire();
					handlers.add(handler);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					handlerLock.release();
				}
			}
		};
		execute(adder);
	}


	@Override
	public void execute(Runnable runnable) {
		input.getExecutor().execute(runnable);
		
	}

	@Override
	public void removeMessageHandler(StreamClientHandler handler) {
		Runnable adder = new Runnable() {
			
			@Override
			public void run() {
				try {
					handlerLock.acquire();
					handlers.remove(handler);
				} catch (Exception e) {
					// TODO: handle exception
				} finally { 
					handlerLock.release();
				}
			}
		};
		execute(adder);

	}

	@Override
	public StreamEntityScanner entityScanner(GNetEntityMatcher entityMatcher, int updateInterval, String retVars)
			throws StreamClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamClientEntitySearch entitySearch(GNetEntityMatcher matcher, String retVars,
			StreamClientEntitySearchCallBack searchObserver) throws StreamClientException {
		StreamClientEntitySearchImpl search = new StreamClientEntitySearchImpl();
		search.init(matcher, retVars, searchObserver, this);
		return search;
	}
	
	private class ServerMessageRouter extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					GNetServerMessage message = connector.getServerMessageQueue().take();
					try {
						handlerLock.acquire();
						for (StreamClientHandler streamClientHandler : handlers) {
							streamClientHandler.onMessage(message);
						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) { 
							return;
						}
						
					} finally { 
						handlerLock.release();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
