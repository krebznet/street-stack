package com.dunkware.xstream.net.server.core;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.server.StreamConnection;
import com.dunkware.xstream.net.server.StreamConnectionChannel;
import com.dunkware.xstream.net.server.StreamConnectionMessageHandler;
import com.dunkware.xstream.net.server.StreamServer;

public class StreamConnectionImpl implements StreamConnection  {

	private String identifier; 
	private StreamConnectionChannel channel; 
	

	
	@Override
	public void startConnection(StreamServer server, String identifier, StreamConnectionChannel channel) {
		this.channel = channel;
		this.identifier = identifier; 
		
	}

	@Override
	public void consumeMessage(GNetClientMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(GNetServerMessage message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMessageHandler(StreamConnectionMessageHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMessageHandler(StreamConnectionMessageHandler handler) {
		// TODO Auto-generated method stub
		
	}

	private class MessageConsumer extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				
			}
		}
	}
	
}
