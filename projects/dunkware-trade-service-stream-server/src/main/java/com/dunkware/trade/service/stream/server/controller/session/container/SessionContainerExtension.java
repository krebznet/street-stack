package com.dunkware.trade.service.stream.server.controller.session.container;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;

public interface SessionContainerExtension {

	public void workerInit(WorkerContainerInput input); 
	
	
	public void containerStart(SessionContainer container);
	
	
	public void sessionClose(SessionContainer container); 
	
	
	public void sessionOpen(SessionContainer container);
	
}
