package com.dunkware.xstream.net.client.core.registry;

import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;
import com.dunkware.xstream.net.client.connector.StreamClientKafkaConnectorType;
import com.dunkware.xstream.net.client.core.connector.StreamClientKafkaConnector;

public class StreamClientRegistry {
	
	private static StreamClientRegistry instance = null; 
	
	public static StreamClientRegistry get() { 
		if(instance == null) { 
			instance = new StreamClientRegistry();
		}
		return instance;
	}
	
	public StreamClientConnector createConnector(StreamClientConnectorType connectorType) throws Exception { 
	
		if (connectorType instanceof StreamClientKafkaConnectorType) { 
			return new StreamClientKafkaConnector();
		}
		
		throw new Exception("Connector type not registered " + connectorType.getClass().getName());
	}
}
