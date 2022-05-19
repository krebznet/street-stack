package com.dunkware.xstream.net.client.core.registry;

import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;

public class StreamClientRegistry {

	private static StreamClientRegistry instance = null; 
	
	public static StreamClientRegistry get() { 
		if(instance == null) { 
			instance = new StreamClientRegistry();
		}
		return instance;
	}
	
	public StreamClientConnector createConnector(StreamClientConnectorType connectorType) throws Exception { 
		return null;
	}
}
