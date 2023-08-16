package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.Document;

import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespBuilder;

public class StreamStatCache {
	
	private String stream; 
	
	private Map<String,StreamEntityStatCache> entities = new ConcurrentHashMap<String,StreamEntityStatCache>();
	
	public void init(String stream) { 
		this.stream = stream;
	}
	
	public void consumeEntitySession(Document document) { 
		StreamEntityStatCache entityCache = entities.get(document.getString("ident"));
		if(entityCache == null) { 
			entityCache = new StreamEntityStatCache();
			entityCache.init(document.getString("ident"));
			entities.put(entityCache.getIdent(), entityCache);
		}
		entityCache.consumeSession(document);
	}
	
	
	public EntityStatResp getStat(EntityStatReq req) {
		EntityStatRespBuilder builder = EntityStatRespBuilder.newInstance();
		StreamEntityStatCache ent = entities.get(req.getEntity());
		if(ent == null) { 
			return builder.exception("Entity " + req.getTarget() + " not found on stream " + stream).build();
		}
		return ent.getStat(req);
	}

}
