package com.dunkware.trade.service.data.worker.stream.session.cache;

public interface DataStreamSessionCacheWorker {
	
	public void start() throws Exception;
	
	public void streamEvent(Object event); 

}
