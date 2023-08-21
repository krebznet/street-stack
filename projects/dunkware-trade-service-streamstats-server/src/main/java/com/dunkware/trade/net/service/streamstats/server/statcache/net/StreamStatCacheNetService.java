package com.dunkware.trade.net.service.streamstats.server.statcache.net;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.messaging.anot.ADunkNetComponent;
import com.dunkware.spring.messaging.anot.ADunkNetService;
import com.dunkware.spring.messaging.core.response.DunkNetServiceResponse;
import com.dunkware.trade.net.service.streamstats.server.statcache.StreamStatCacheService;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;

@ADunkNetComponent
public class StreamStatCacheNetService {
	
	
	@Autowired
	private StreamStatCacheService cache;
	
	
	@ADunkNetService()
	public DunkNetServiceResponse entityStatReq(EntityStatReq req) { 
		return null;
	}
	

}
