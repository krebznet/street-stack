package com.dunkware.trade.service.stream.worker;

import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.xstream.api.XStream;

public interface StreamSessionWorker {

	XStream getXStream();
	
	void start(StreamSessionWorkerStartReq req) throws Exception;
	
	void stop() throws Exception;
	
	StreamSessionWorkerStats getStats();
}
