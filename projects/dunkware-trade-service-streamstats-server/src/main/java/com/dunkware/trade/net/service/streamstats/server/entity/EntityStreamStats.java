package com.dunkware.trade.net.service.streamstats.server.entity;

import java.util.List;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatQuery;
import com.dunkware.xstream.model.stats.entity.EntityStats;

import io.vertx.core.Future;

public interface EntityStreamStats {
	
	public String getStreamIdentifier();
	
	public int getStreamId();
	
	public Future<EntityStats> query(EntityStatQuery query) throws Exception;
	
	public EntityStreamStatsBean getBean();
	
	public MySqlConnectionPool getConnectionPool();
	
	public void insert(List<EntityStat> stats) throws Exception;
	
	public double deleteAll() throws Exception;
}
