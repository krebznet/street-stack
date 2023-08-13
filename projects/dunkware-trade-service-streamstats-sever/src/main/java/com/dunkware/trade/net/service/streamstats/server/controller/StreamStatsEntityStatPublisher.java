package com.dunkware.trade.net.service.streamstats.server.controller;

import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsService;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatReqType;
import com.dunkware.xstream.model.stats.EntityStatResp;

public class StreamStatsEntityStatPublisher  {

	
	public StreamStatsEntityStatPublisher(StreamStatsService statsService, EntityStatReq req) { 
		
	}
	
	

	public EntityStatResp handle(StreamStats stats, EntityStatReq req) { 
		req.getDate();
		req.getRelativeDays();
		// method to get the days can resolve; 
		if(req.getType() == EntityStatReqType.VarHighRelative) { 
			// 
		}
		
		EntityStatResp resp  = new EntityStatResp();
		return resp;
				
	
	}
}
