package com.dunkware.trade.net.service.streamstats.client.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RequestCallback;

import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.stopwatch.DStopWatchPool;
import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.core.request.DunkNetServiceRequest;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClient;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClientException;
import com.dunkware.trade.net.service.streamstats.client.StreamStatsClientInput;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public class StreamStatNetClientImpl implements StreamStatsClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private StreamStatsClientInput input;

	private boolean disposed = true;

	
	private DStopWatchPool pool = DStopWatchPool.createPool(400);
	
	
	@Override
	public void start(DunkNet input) throws StreamStatsClientException {
		DunkNet net;
		try {
			
		} catch (Exception e) {
		
		
		}
		
		disposed = false;
	}

	
	@Override
	public void dispose() {
		if (!disposed) {
			
			disposed = true;
		}

	}

	
	@Override
	public EntityStatResp getEntityStat(EntityStatReq req) throws Exception {
		// TODO Auto-generated method stub
		RequestCallback callback = input.getDunkNet().service(req);
		// need to figure out the callback here. 
		
		
		return null;
	}


	public void releaseWatch(DStopWatch watch) { 
		pool.release(watch);
	}


	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}


	
	

	
}
