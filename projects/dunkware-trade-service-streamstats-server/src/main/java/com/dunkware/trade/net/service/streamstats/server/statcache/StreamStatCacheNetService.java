package com.dunkware.trade.net.service.streamstats.server.statcache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.xstream.model.stats.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;


@Service
public class StreamStatCacheNetService extends DunkNetComponent {
	
	
	@Autowired
	private StreamStatCacheService cache;
	
	
	@PostConstruct
	public void postConstruct() { 
		System.out.println("okay");
	}
	
	@ADunkNetService(label =  "Entity Stat Request")
	public EntityStatResp entityStatReq(EntityStatReq req) {
		return cache.entityStat(req);
	}
	
	@ADunkNetService(label = "Entity Stat Bulk Request")
	public EntityStatBulkResp entityStatBulkReq(EntityStatBulkReq req) { 
		return cache.entityBulkStat(req);
	}
	

}
