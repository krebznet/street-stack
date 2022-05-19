package com.dunkware.xstream.net.client.core.connector;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;
import com.dunkware.xstream.net.client.connector.StreamClientGrpcConnectorType;

public class StreamClientGrpcConnector implements StreamClientConnector {

	private StreamClientGrpcConnectorType myType; 
	@Override
	public void connect(StreamClientConnectorType config) throws StreamClientException {
		try {
			myType = (StreamClientGrpcConnectorType)config;
		} catch (Exception e) {
			throw new StreamClientException("Exception casting grpc connector type " + e.toString());
		}
		
		
	}

	@Override
	public void sendMessage(GNetClientMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object consume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}
