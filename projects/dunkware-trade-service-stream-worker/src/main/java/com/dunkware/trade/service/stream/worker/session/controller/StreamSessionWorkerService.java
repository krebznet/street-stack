package com.dunkware.trade.service.stream.worker.session.controller;

import java.util.Collection;

import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;

public interface StreamSessionWorkerService {

	Collection<StreamSessionWorker> getWorkers() throws Exception; 
	
	StreamSessionWorker getWorker(String workerId) throws Exception;
	
	StreamSessionWorker startWorker(StreamSessionWorkerStartReq req) throws Exception;
	
	void stopWorker(String workerId) throws Exception;
}
