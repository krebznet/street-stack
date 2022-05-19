package com.dunkware.xstream.net.server;

import com.dunkware.xstream.net.server.connector.StreamServerConnectorType;

public interface StreamServerConnector {
	
	public void start(StreamServerConnectorType type, StreamServer server) throws Exception;
	
	public void close();
	
	// this will build the factory or the message channel for a client. 
	// it will have to do this tricky. 

}
