package com.dunkware.trade.service.data.service.stream.container.connection.extensions;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamConnectionExtension;
import com.dunkware.trade.service.data.service.stream.container.connection.StreamContainerConnection;
import com.dunkware.trade.service.data.service.stream.container.connection.runners.EntitySearchRunner;
import com.dunkware.xstream.net.core.util.GNetProto;

public class EntitySearchExtension implements StreamConnectionExtension {

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
	public void onMessage(GNetClientMessage message) {
		if(GNetProto.isClientEntitySearchRequest(message)) {
			EntitySearchRunner runner = new EntitySearchRunner();
			runner.init(message.getEntitySearchRequest(), connection);
			controller.getExecutor().execute(runner);
		}
 	}

	
	

}
