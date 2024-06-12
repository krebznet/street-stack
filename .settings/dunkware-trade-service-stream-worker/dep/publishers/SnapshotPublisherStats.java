package com.dunkware.trade.service.stream.worker.session.publishers;

public class SnapshotPublisherStats {
	
	private long maxPublishTime;
	private int publishCount; 
	private int entityCount;
	
	public int getPublishCount() {
		return publishCount;
	}
	public void setPublishCount(int publishCount) {
		this.publishCount = publishCount;
	}
	
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public long getMaxPublishTime() {
		return maxPublishTime;
	}
	public void setMaxPublishTime(long maxPublishTime) {
		this.maxPublishTime = maxPublishTime;
	}
	
	
	
	
	

}
