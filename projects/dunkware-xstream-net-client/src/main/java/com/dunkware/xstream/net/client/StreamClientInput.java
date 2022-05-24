package com.dunkware.xstream.net.client;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;

public class StreamClientInput {
	
	private DExecutor executor; 
	private StreamClientConnectorType connectorType;
	private String clientIdentifier; 
	private String stream; 
	
	public DExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DExecutor executor) {
		this.executor = executor;
	}
	public StreamClientConnectorType getConnectorType() {
		return connectorType;
	}
	public void setConnectorType(StreamClientConnectorType connectorType) {
		this.connectorType = connectorType;
	}
	public String getClientIdentifier() {
		return clientIdentifier;
	}
	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	} 
	
	
	
	

}
