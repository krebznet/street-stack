package com.dunkware.utils.core.concurrent;

public class DunkExecutorStats {
	
	private int poolSize; 
	private long completedCount; 
	private long pendingCount; 
	private long timeoutCount;
	private long pendingTasks;
	public int getPoolSize() {
		return poolSize;
	}
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	public long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(long completedCount) {
		this.completedCount = completedCount;
	}
	public long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public long getTimeoutCount() {
		return timeoutCount;
	}
	public void setTimeoutCount(long timeoutCount) {
		this.timeoutCount = timeoutCount;
	}
	public long getPendingTasks() {
		return pendingTasks;
	}
	public void setPendingTasks(long pendingTasks) {
		this.pendingTasks = pendingTasks;
	}
	
	
	
	

}
