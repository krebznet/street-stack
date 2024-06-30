package com.dunkware.stream.data.injest.consumers;

public class StreamSessionConsumerMetrics {
	
	private long injestCount; 
	private int queueSize;
	
	public long getInjestCount() {
		return injestCount;
	}
	public void setInjestCount(long injestCount) {
		this.injestCount = injestCount;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	} 
	
	

}
