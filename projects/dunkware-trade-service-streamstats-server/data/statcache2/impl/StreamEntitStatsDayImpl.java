package com.dunkware.trade.net.service.streamstats.server.statcache2.impl;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamEntityStatsDay;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStats;

public class StreamEntitStatsDayImpl implements StreamEntityStatsDay  {

	private LocalDate date; 
	private Map<Integer,EntityStat> sats = new ConcurrentHashMap<Integer, EntityStat>();
	
	
	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public boolean statExists(int type, int target) {
		return false; 
	}

	@Override
	public EntityStat getStat(int type, int target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
