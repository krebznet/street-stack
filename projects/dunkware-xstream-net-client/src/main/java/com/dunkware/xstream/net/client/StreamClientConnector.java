package com.dunkware.xstream.net.client;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;

public interface StreamClientConnector {
	
	public void connect(StreamClientConnectorType config) throws StreamClientException;

	public void sendMessage(GNetClientMessage message) throws StreamClientException;
	
	public Object consume();
	
	public boolean isConnected();
}
