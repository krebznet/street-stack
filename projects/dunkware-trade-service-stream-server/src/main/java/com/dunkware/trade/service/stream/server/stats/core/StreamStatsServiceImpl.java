package com.dunkware.trade.service.stream.server.stats.core;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.server.stats.StreamStatsService;
import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;
import com.dunkware.xstream.core.stats.StreamStats;

@Service
public class StreamStatsServiceImpl implements StreamStatsService   {
	
	@Autowired
	private StreamEntityDayStatsRepo statsRepo;
	
	private ConcurrentHashMap<String,StreamStats> streamStats = new ConcurrentHashMap<String,StreamStats>();

	
	@PostConstruct
	private void init() { 
		
	}
	
	
	@Override
	public StreamStats getStreamStats(String streamIdent) throws Exception {
		if(streamStats.get(streamIdent) == null) 
			throw new Exception("Stream Stats not found for " + streamIdent);
		return streamStats.get(streamIdent);
	}
	
	
	

}
