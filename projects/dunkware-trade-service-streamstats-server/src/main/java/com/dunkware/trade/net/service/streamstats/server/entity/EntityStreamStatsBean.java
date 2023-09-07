package com.dunkware.trade.net.service.streamstats.server.entity;

public class EntityStreamStatsBean {

	private String identifier;
	private long insertCount = 0;
	private int insertQueue = 0;
	private int insertLastSecond = 0;
	private int insertMaxSecond = 0;
	
	public long getInsertCount() {
		return insertCount;
	}
	public void setInsertCount(long insertCount) {
		this.insertCount = insertCount;
	}
	public int getInsertQueue() {
		return insertQueue;
	}
	public void setInsertQueue(int insertQueue) {
		this.insertQueue = insertQueue;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getInsertLastSecond() {
		return insertLastSecond;
	}
	public void setInsertLastSecond(int insertLastSecond) {
		this.insertLastSecond = insertLastSecond;
	}
	public int getInsertMaxSecond() {
		return insertMaxSecond;
	}
	public void setInsertMaxSecond(int insertMaxSecond) {
		this.insertMaxSecond = insertMaxSecond;
	}
	
	
	
	
	
	
	
	
}
