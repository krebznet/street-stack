package com.dunkware.trade.service.stream.worker;

import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;

public interface StreamSessionWorkerService {

	StreamSessionWorker getWorker(String workerId) throws Exception;
	
	StreamSessionWorker startWorker(StreamSessionWorkerStartReq req) throws Exception;
	
	void stopWorker(String workerId) throws Exception;
}
