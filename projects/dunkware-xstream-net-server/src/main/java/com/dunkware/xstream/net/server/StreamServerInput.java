package com.dunkware.xstream.net.server;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.net.server.connector.StreamServerConnectorType;

public class StreamServerInput {

	private DExecutor executor; 
	private StreamServerConnectorType connectorType; 
	
	public StreamServerConnectorType getConnectorType() { 
		return connectorType;
	}
	
	public DExecutor getExecutor() { 
		return executor; 
	}
}
