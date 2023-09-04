package com.dunkware.trade.net.service.streamstats.server.statcache;

public class StreamStatsCacheServiceBean {
	
	private long loadTime; 
	private boolean loaded; 
	private long loadCount;
	private int entityCount = 0;
	private long loadBatch;
	private long documents = 0;
	private long countTime = 0;
	private String completed;
	
	public long getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	public long getLoadCount() {
		return loadCount;
	}
	public void setLoadCount(long loadCount) {
		this.loadCount = loadCount;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public long getLoadBatch() {
		return loadBatch;
	}
	public void setLoadBatch(long loadBatch) {
		this.loadBatch = loadBatch;
	}
	public long getDocuments() {
		return documents;
	}
	public void setDocuments(long documents) {
		this.documents = documents;
	}
	public long getCountTime() {
		return countTime;
	}
	public void setCountTime(long countTime) {
		this.countTime = countTime;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	
	
	
	
	
	
	
	
	

}
