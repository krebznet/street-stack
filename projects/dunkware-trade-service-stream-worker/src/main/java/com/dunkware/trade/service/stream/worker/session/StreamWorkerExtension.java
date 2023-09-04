package com.dunkware.trade.service.stream.worker.session;

public interface StreamWorkerExtension {

	void init(StreamWorkerChannel worker);
	
	void start();
	
	void stop();
}
