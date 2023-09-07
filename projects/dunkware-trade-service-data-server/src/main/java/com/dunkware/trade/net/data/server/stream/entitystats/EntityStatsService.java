package com.dunkware.trade.net.data.server.stream.entitystats;

import java.util.Collection;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

public interface EntityStatsService {

	public EntityStreamStats getStream(String streamIdentifier) throws Exception; 
	
	public Collection<EntityStreamStats> getStreams();
	
}
