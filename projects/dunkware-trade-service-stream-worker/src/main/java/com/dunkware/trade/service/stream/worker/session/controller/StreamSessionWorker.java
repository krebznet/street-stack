package com.dunkware.trade.service.stream.worker.session.controller;

import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.model.snapshot.EntitySnapshot;

public interface StreamSessionWorker {

	XStream getXStream();
	
	void start(StreamSessionWorkerStartReq req) throws Exception;
	
	void stop() throws Exception;
	
	StreamSessionWorkerStats getStats();
	
	Channel getChannel();
	
	EntitySnapshot getEntitySnapshot(String ident) throws Exception;
}
