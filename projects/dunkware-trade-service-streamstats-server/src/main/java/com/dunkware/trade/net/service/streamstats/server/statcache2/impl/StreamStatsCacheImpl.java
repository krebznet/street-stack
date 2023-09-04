package com.dunkware.trade.net.service.streamstats.server.statcache2.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.net.service.streamstats.server.runtime.impl.StreamStatsImpl;
import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamEntityStats;
import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamStatsCache;

public class StreamStatsCacheImpl implements StreamStatsCache {

	private StreamStatsImpl stream; 
	
	private Map<Integer,StreamEntityStats> entityStats = new ConcurrentHashMap<Integer,StreamEntityStats>();
	
	
	@Override
	public int getId() {
		return stream.getStreamid();
	}

	@Override
	public String getIdentifier() {
		return stream.getStreamIdentifier();
	}

	@Override
	public StreamEntityStats getEntityStats(int id) {
		return entityStats.get(id);
	}

	@Override
	public StreamEntityStats getEntityStatsi(String ident) {
		return null;
	}

}
