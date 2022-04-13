package com.dunkware.trade.service.stream.server.session.worker;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatsSpec;
import com.dunkware.trade.service.stream.server.session.worker.protocol.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.server.session.worker.protocol.spec.StreamSessionWorkerStatsSpec;
import com.dunkware.xstream.api.XStream;

public interface StreamSessionWorker {

	XStream getXStream();
	
	void start(StreamSessionWorkerStartReq req) throws Exception;
	
	void stop() throws Exception;
	
	StreamSessionWorkerStatsSpec getStats();
}
