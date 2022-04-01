package com.dunkware.trade.service.stream.server.store;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.service.stream.server.store.repository.StreamStoreDO;

public class StreamStore {

	private StreamStoreDO entity; 
	private MySqlConnectionPool pool;
	
	public StreamStore(StreamStoreDO ent) throws Exception { 
		this.entity = ent; 
		pool = new MySqlConnectionPool(StreamStoreDO.toConnection(ent),3);
	}
	
	public MySqlConnectionPool getPool() { 
		return pool;
	}
	
	public StreamStoreDO getEntity() { 
		return entity;
	}
	
	public boolean isActive() { 
		return entity.isActive();
	}


}
