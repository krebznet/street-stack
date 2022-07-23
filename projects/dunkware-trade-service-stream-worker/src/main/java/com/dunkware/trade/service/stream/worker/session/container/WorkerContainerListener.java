package com.dunkware.trade.service.stream.worker.session.container;

public interface WorkerContainerListener {
	
	
	public void onMessage(Object message);
	
	
	public void onReset(WorkerContainer container);

}
