package com.dunkware.trade.net.data.server.stream.entitystats;

import java.util.List;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public interface EntityStreamStats {
	
	public String getStreamIdentifier();
	
	public int getStreamId();
	
	public EntityStreamStatsBean getBean();
	
	public MySqlConnectionPool getConnectionPool();
	
	public void insert(List<EntityStat> stats) throws Exception;
	
	public double deleteAll() throws Exception;
	
	public StreamDescriptor getStreamDescriptor();
}
