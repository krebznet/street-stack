package com.dunkware.trade.service.data.service.stream.container.connection.handlers;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamConnectionHandler;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamContainerConnection;

public class EntitySearchHandler implements StreamConnectionHandler {

	private StreamContainerController controller; 
	private StreamContainerConnection connection; 
	
	
	@Override
	public void create(StreamContainerController controller, StreamContainerConnection connection) {
		this.controller = controller;
		this.connection = connection;
	// StreamConnectionExtension
		// 
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void handleMessage(GNetClientMessage message) {
		
		
	}

	
	

}
