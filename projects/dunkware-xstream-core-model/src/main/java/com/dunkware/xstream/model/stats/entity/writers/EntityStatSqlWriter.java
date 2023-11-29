package com.dunkware.xstream.model.stats.entity.writers;

import java.util.concurrent.BlockingQueue;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatSqlWriter {
	
	private MySqlConnectionPool pool;
	private BlockingQueue<EntityStat> stat; 
	
	
	

}
