package com.dunkware.trade.net.data.server.stream.entitystats.cache.loader;

public class StatCacheLoaderBatch {

	private int entityStart;
	private int entityStop;

	public StatCacheLoaderBatch(int entityStart, int entityStop) {
		this.entityStop = entityStop;
		this.entityStart = entityStart;
	}

	public int getEntityStart() {
		return entityStart;
	}

	public void setEntityStart(int entityStart) {
		this.entityStart = entityStart;
	}

	public int getEntityStop() {
		return entityStop;
	}

	public void setEntityStop(int entityStop) {
		this.entityStop = entityStop;
	}
	
	



}
