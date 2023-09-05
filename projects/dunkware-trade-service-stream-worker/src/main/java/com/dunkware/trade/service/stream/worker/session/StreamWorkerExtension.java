package com.dunkware.trade.service.stream.worker.session;

public interface StreamWorkerExtension {

	void init(StreamWorker worker) throws Exception;
	
	void start() throws Exception;
	
	void stop() throws Exception;
}
