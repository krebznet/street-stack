package com.dunkware.trade.service.stream.server.stats;

import com.dunkware.xstream.core.stats.StreamStats;

public interface StreamStatsService {

	/**
	 * Okay gets the stream stats cache for a straem. 
	 * @param streamIdent
	 * @return
	 * @throws Exception
	 */
	StreamStats getStreamStats(String streamIdent) throws Exception; 
	
	
	
	
	
}
