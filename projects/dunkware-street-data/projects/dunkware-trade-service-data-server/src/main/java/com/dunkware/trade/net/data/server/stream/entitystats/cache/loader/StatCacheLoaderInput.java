package com.dunkware.trade.net.data.server.stream.entitystats.cache.loader;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

public class StatCacheLoaderInput {

	private int maxEntityId; 
	private String statsTable; 
	private MySqlConnectionPool pool;
	private int loaderThreadCount; 
	private int loadBatchEntityCount;
	
	public int getMaxEntityId() {
		return maxEntityId;
	}
	public void setMaxEntityId(int maxEntityId) {
		this.maxEntityId = maxEntityId;
	}
	public String getStatsTable() {
		return statsTable;
	}
	public void setStatsTable(String statsTable) {
		this.statsTable = statsTable;
	}
	public MySqlConnectionPool getPool() {
		return pool;
	}
	public void setPool(MySqlConnectionPool pool) {
		this.pool = pool;
	}
	public int getLoaderThreadCount() {
		return loaderThreadCount;
	}
	public void setLoaderThreadCount(int loaderThreadCount) {
		this.loaderThreadCount = loaderThreadCount;
	}
	public int getLoadBatchEntityCount() {
		return loadBatchEntityCount;
	}
	public void setLoadBatchEntityCount(int loadBatchEntityCount) {
		this.loadBatchEntityCount = loadBatchEntityCount;
	} 
	
	
}
