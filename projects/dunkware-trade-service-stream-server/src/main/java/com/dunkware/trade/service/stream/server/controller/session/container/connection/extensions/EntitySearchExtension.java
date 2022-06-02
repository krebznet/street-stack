package com.dunkware.trade.service.stream.server.controller.session.container.connection.extensions;

import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.SessionContainerExtension;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.SessionContainerConnection;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.runners.EntitySearchRunner;
import com.dunkware.xstream.net.core.util.GNetProto;

public class EntitySearchExtension implements SessionContainerExtension {

	private SessionContainer controller; 
	private SessionContainerConnection connection; 
	
	@Override
	public void create(SessionContainer controller, SessionContainerConnection connection) {
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
