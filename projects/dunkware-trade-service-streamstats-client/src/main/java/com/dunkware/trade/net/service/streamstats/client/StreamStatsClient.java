package com.dunkware.trade.net.service.streamstats.client;

import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

public interface StreamStatsClient {
	
	void start(DunkNet net) throws StreamStatsClientException; 
	
	void stop();
	
	void dispose();
	
	EntityStatResp getEntityStat(EntityStatReq req) throws Exception;

}
