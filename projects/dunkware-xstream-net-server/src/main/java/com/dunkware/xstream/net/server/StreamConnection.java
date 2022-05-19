package com.dunkware.xstream.net.server;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;

public interface StreamConnection {
	
	public void startConnection(StreamServer server, String identifier, StreamConnectionChannel channel);
	
	public void consumeMessage(GNetClientMessage message);
	
	public void sendMessage(GNetServerMessage message) throws Exception;

	public void addMessageHandler(StreamConnectionMessageHandler handler);
	
	public void removeMessageHandler(StreamConnectionMessageHandler handler);
	
	
}

// GRPC netStreamClient(stream client messages), serverMessages); 
// so that will get passed into a stream connection. 

// StreamConnectionChannel -> this is provided by the connector
	// grpc --> implements it own channel nd it has the response observer etc. 
// 