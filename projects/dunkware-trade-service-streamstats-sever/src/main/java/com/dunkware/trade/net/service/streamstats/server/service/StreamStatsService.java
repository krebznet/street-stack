package com.dunkware.trade.net.service.streamstats.server.service;

import com.mongodb.client.MongoCollection;

public interface StreamStatsService {

	/**
	 * Okay gets the stream stats cache for a straem. 
	 * @param streamIdent
	 * @return
	 * @throws Exception
	 */
	StreamStats getStreamStats(String streamIdent) throws Exception; 
	
	
	
	
	
	
}
