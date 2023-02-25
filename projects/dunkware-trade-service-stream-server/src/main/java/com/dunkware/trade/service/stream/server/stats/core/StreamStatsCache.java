package com.dunkware.trade.service.stream.server.stats.core;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsDoc;
import com.dunkware.xstream.model.stats.StreamEntityDayStats;
import com.dunkware.xstream.model.stats.StreamEntityStats;


public class StreamStatsCache {
	
	private String stream; 
	private ConcurrentHashMap<Integer, StreamEntityStats> entityStats = new ConcurrentHashMap<Integer, StreamEntityStats>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	public StreamStatsCache(String stream) { 
		this.stream = stream; 
	}
	
	public String getStream() { 
		return stream; 
	}
	
	public void insert(StreamEntityDayStatsDoc dayStatsDoc) {
		StreamEntityStats stats = entityStats.get(dayStatsDoc.getEntId());
		if(stats == null) { 
			stats = new StreamEntityStats();
		}
		StreamEntityDayStats dayStats = StreamStatsUtils.toDayStats(dayStatsDoc);
		if(!stats.hasDate(dayStats.getDate())) { 
			stats.getDays().add(dayStats); 
		}
		entityStats.put(dayStatsDoc.getEntId(), stats);
	}
	
	
	
	

}
