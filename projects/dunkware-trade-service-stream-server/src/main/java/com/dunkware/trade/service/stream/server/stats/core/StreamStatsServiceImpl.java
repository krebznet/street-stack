package com.dunkware.trade.service.stream.server.stats.core;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.server.stats.repository.StreamEntityDayStatsRepo;

@Service
public class StreamStatsServiceImpl   {
	
	@Autowired
	private StreamEntityDayStatsRepo statsRepo;
	
	private Map<String,StreamStatsCache> streamCache;
	
	
	
	// listens for a session complete
	// loads the entity stats at startup cache 

	// getStats(AAPL) 
	//	sessionCount
	
	// PUT 
	// varAggHistorical(HIGH,5); 
	
	private class EntityStats { 
		
		// day stats 
		// 
		// get all 
		
		// 
	}

}
