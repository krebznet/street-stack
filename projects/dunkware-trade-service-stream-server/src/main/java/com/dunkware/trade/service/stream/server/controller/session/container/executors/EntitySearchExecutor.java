package com.dunkware.trade.service.stream.server.controller.session.container.executors;

import java.util.List;

import com.dunkware.net.proto.cluster.GContainerServerMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerHandler;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerWorker;
import com.dunkware.xstream.model.search.SessionEntitySearch;

public class EntitySearchExecutor implements SessionContainerHandler {

	private SessionContainer container; 
	
	public EntitySearchExecutor(SessionContainer container) { 
		this.container = container; 
	}
	
	public Object execute(SessionEntitySearch search, List<String> retVars) throws Exception { 
		container.addMessageHandler(this);
		// need to send a message to all nodes? --
		List<SessionContainerWorker> workers = container.getWorkers();
		
		container.getWorkers();
		
		return null;
	}

	@Override
	public void onControllerMessage(GContainerServerMessage message) {
		
		// TODO Auto-generated method stub
		
	}
	
	
}
