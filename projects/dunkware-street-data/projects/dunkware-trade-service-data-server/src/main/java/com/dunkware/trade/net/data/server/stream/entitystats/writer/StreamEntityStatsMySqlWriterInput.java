package com.dunkware.trade.net.data.server.stream.entitystats.writer;

import java.util.List;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class StreamEntityStatsMySqlWriterInput {
	
	
	private List<EntityStat> stats; 
	private MySqlConnectionPool connectionPool; 

}
