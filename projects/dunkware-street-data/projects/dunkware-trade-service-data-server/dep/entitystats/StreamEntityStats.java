package com.dunkware.trade.net.data.server.stream.entitystats;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.StatCache;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;
import com.dunkware.trade.service.data.model.entitystats.EntityStatRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatResponse;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;

public interface StreamEntityStats {
	
	public String getStreamIdentifier();
	
	public int getStreamId();
	
	public MySqlConnectionPool getConnectionPool();
	
	public StreamDescriptor getStreamDescriptor();
	
	public StatCache getCache();
	 
	public EntityStatAggResponse statAgg(EntityStatAggRequest req);
	
	public EntityStatResponse statRequest(EntityStatRequest req);
}
