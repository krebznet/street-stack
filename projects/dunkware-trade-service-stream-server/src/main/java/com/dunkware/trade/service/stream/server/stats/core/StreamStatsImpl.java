package com.dunkware.trade.service.stream.server.stats.core;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.stats.StreamEntityStats;
import com.dunkware.trade.service.stream.server.stats.StreamStats;

public class StreamStatsImpl implements StreamStats {
	
	private StreamController stream; 
	private Map<String,StreamEntityStats> entities = new ConcurrentHashMap<String,StreamEntityStats>();
	

	@Override
	public StreamController getStream() {
		return stream; 
	}

	@Override
	public StreamEntityStats getEntity(String ident) throws Exception {
		if(entities.get(ident) == null) { 
			throw new Exception("Entity Stats " + ident + " not found");
		}
		return entities.get(ident);
	}

	@Override
	public Collection<StreamEntityStats> getEntities() {
		return entities.values();
	}
	
	
	

	
	
	
	

}
