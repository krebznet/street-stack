package com.dunkware.stream.data.injest.consumers;

public class EntityStatsConsumerMetrics {
	
	private int statLoadCount; 
	private int statLoadQueue;
	private int statConsumerQueue;
	
	public int getStatLoadCount() {
		return statLoadCount;
	}
	public void setStatLoadCount(int statLoadCount) {
		this.statLoadCount = statLoadCount;
	}
	public int getStatLoadQueue() {
		return statLoadQueue;
	}
	public void setStatLoadQueue(int statLoadQueue) {
		this.statLoadQueue = statLoadQueue;
	}
	public int getStatConsumerQueue() {
		return statConsumerQueue;
	}
	public void setStatConsumerQueue(int statConsumerQueue) {
		this.statConsumerQueue = statConsumerQueue;
	}
	
	
	

}
