package com.dunkware.trade.service.stream.worker.session.container;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerExtType;

/**
 * Allows us to add worker container extensions that
 * can handle messages -- we need to add native JSON 
 * message- 
 * @author duncankrebs
 *
 */
public interface WorkerContainerExt {

	public void init(WorkerContainerExtType extType, WorkerContainer container); 
	
	public void start();
}
