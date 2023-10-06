package com.dunkware.trade.net.data.server.stream.entitystats;

import java.util.Collection;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

public interface StreamEntityStatsProvider {

	public StreamEntityStats getStream(String streamIdentifier) throws Exception; 
	
	public Collection<StreamEntityStats> getStreams();
	
}
