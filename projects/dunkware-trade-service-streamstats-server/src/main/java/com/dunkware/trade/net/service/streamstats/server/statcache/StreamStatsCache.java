package com.dunkware.trade.net.service.streamstats.server.statcache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.Document;

import com.dunkware.xstream.model.stats.EntityStatBulkReq;
import com.dunkware.xstream.model.stats.EntityStatBulkResp;
import com.dunkware.xstream.model.stats.EntityStatReq;
import com.dunkware.xstream.model.stats.EntityStatResp;
import com.dunkware.xstream.model.stats.EntityStatRespBuilder;
import com.dunkware.xstream.model.stats.EntityStatRespType;

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
	
	public EntityStatBulkResp getBulkStat(EntityStatBulkReq req) {
		List<StreamEntityStatCache> resolvedEntities = new ArrayList<StreamEntityStatCache>();
		
		List<String> unresolvedEntities = new ArrayList<String>();
		if(req.getEntities() == null) { 
			EntityStatBulkResp resp = new EntityStatBulkResp();
			resp.setSuccess(false);
			resp.setException("Entities is null");
			return resp;
		}
		for (String string : req.getEntities()) {
			StreamEntityStatCache cache  = entities.get(string);
			if(cache == null) { 
				unresolvedEntities.add(string);
			} else {
				resolvedEntities.add(cache);
			}
		}
		Map<String,String> statExceptions = new HashMap<String,String>();
		Map<String,Number> resolvedStats = new HashMap<String,Number>();
		Map<String,Integer> unresolvedStats = new HashMap<String,Integer>();
		EntityStatReq statReq = new EntityStatReq();
		statReq.setAgg(req.getAgg());
		statReq.setDate(req.getDate());
		statReq.setRelativeDays(req.getRelativeDays());
		statReq.setStream(req.getTarget());
		statReq.setType(req.getType());
		statReq.setTarget(req.getTarget());
		
		for (StreamEntityStatCache ent : resolvedEntities) {
			EntityStatResp resp = ent.getStat(statReq);
			if(resp.getType() == EntityStatRespType.Exception) { 
				statExceptions.put(ent.getIdent(), resp.getException());
				continue;
			}
			if(resp.getType() == EntityStatRespType.Unresolved) {
				unresolvedStats.put(ent.getIdent(), new Integer(1));
				continue;
			}
			if(resp.getType() == EntityStatRespType.Resolved) { 
				resolvedStats.put(ent.getIdent(),resp.getValue());
			}
			
		}
		
		EntityStatBulkResp resp = new EntityStatBulkResp();
		resp.setExceptionStats(statExceptions);
		resp.setUnresolvedStats(unresolvedStats);
		resp.setResolvedStats(resolvedStats);
		
		return resp;
	}
	
	public EntityStatResp getStat(EntityStatReq req) {
		EntityStatRespBuilder builder = EntityStatRespBuilder.newInstance();
		StreamEntityStatCache ent = entities.get(req.getEntity());
		if(ent == null) { 
			return builder.exception("Entity " + req.getTarget() + " not found on stream " + stream).build();
		}
		return ent.getStat(req);
	}
	
	public StreamEntityStatCache getEntity(String ident) throws Exception { 
		StreamEntityStatCache entity = entities.get(ident);
		if(entity == null) { 
			throw new Exception("Stream Entity " + ident + " not found on stream " + stream);
		}
		return entity;
	}

}
