package com.dunkware.trade.service.data.service.stream.container.connection.handlers;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerHandler;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamConnectionHandler;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamContainerConnection;

public class EntityScannerHandler implements StreamConnectionHandler, StreamContainerHandler {

	private StreamContainerController controller; 
	private StreamContainerConnection connection; 
	
	
	@Override
	public void create(StreamContainerController controller, StreamContainerConnection connection) {
		this.controller = controller;
		this.connection = connection;
		controller.addMessageHandler(this);
	}

	
	@Override
	public void handleMessage(GNetClientMessage message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		controller.removeMessageHandler(this);
	}


	@Override
	public void onControllerMessage(GContainerServerMessage message) {
		// TODO Auto-generated method stub
		
	}
	
	

}
