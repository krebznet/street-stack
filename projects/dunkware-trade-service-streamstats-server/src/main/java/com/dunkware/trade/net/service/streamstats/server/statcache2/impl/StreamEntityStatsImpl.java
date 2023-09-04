package com.dunkware.trade.net.service.streamstats.server.statcache2.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamEntityStats;
import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamEntityStatsDay;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.excepton.StatsResolveException;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatResp;

public class StreamEntityStatsImpl implements StreamEntityStats {

	private Map<LocalDate,StreamEntityStatsDay> days = new ConcurrentHashMap<LocalDate,StreamEntityStatsDay>();
	private int id; 
	private String identifier; 
	
	
	public void consumeStat(EntityStat stat) { 
		StreamEntityStatsDay day = days.get(stat.getDate());
		if(day == null) {
			
		}
	}
	
	@Override
	public Collection<StreamEntityStatsDay> getDays() {
		return days.values();
	}
	
	
	@Override
	public List<StreamEntityStatsDay> getRelativeRange(LocalDate relativeDate, int relativeDays, boolean dayGaps)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		return id; 
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	
	@Override
	public EntityStatResp statRequest(EntityStatReq req) throws StatsResolveException {
		//req.getDate(); -> relative session
		// come up with the set if we have 
		// then for each one, getAggType 
		
		// TODO Auto-generated method stub
		return null;
	}

	
}
