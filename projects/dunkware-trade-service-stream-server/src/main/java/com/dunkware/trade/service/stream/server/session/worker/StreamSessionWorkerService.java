package com.dunkware.trade.service.stream.server.session.worker;

import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStartReq;

public interface StreamSessionWorkerService {

	StreamSessionWorker getWorker(String workerId) throws Exception;
	
	StreamSessionWorker startWorker(StreamSessionWorkerStartReq req) throws Exception;
	
	void stopWorker(String workerId) throws Exception;
}
