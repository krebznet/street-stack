package com.dunkware.xstream.net.client;

import java.util.concurrent.BlockingQueue;

import com.dunkware.net.proto.netstream.GNetClientConnectResponse;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;

public interface StreamClientConnector {
	
	public void connect(StreamClientConnectorType connectorType, String clientIdentifier, String stream) throws StreamClientException;

	public void sendMessage(GNetClientMessage message) throws StreamClientException;
	
	public BlockingQueue<GNetServerMessage> getServerMessageQueue();
	
	public boolean isConnected();
	
	public GNetClientConnectResponse getConnectResponse() throws StreamClientException;
}
