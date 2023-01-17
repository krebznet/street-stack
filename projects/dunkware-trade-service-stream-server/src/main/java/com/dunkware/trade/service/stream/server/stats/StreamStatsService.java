package com.dunkware.trade.service.stream.server.stats;

import com.dunkware.xstream.model.stats.StreamStats;

public interface StreamStatsService {

	
	public void insertStats(StreamStats stats) throws Exception;
	
}
