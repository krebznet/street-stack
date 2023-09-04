package com.dunkware.trade.net.service.streamstats.server.statcache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetComponent;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.proto.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

@Profile("StatCache")
@Service
public class StreamStatsCacheNetService extends DunkNetComponent {
	
	
	@Autowired
	private StreamStatsCacheService cache;
	
	@Autowired
	DunkNet dunkNet;
	
	@PostConstruct
	public void postConstruct() {
		try {
		dunkNet.extension(this);			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

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
