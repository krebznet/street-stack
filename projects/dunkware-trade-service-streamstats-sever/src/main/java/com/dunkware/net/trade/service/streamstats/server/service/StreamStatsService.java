package com.dunkware.net.trade.service.streamstats.server.service;

public interface StreamStatsService {

	/**
	 * Okay gets the stream stats cache for a straem. 
	 * @param streamIdent
	 * @return
	 * @throws Exception
	 */
	StreamStats getStreamStats(String streamIdent) throws Exception; 
	
	
	
	
	
}
