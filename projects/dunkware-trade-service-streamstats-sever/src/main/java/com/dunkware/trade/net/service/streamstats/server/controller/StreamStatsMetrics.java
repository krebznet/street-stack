package com.dunkware.trade.net.service.streamstats.server.controller;

public class StreamStatsMetrics {
	
	private int completedRequests; 
	private int pendingRequests; 
	private double lastRequestTime; 
	private int errorCount;
	private long requestCount;
	
	
	public int getCompletedRequests() {
		return completedRequests;
	}
	public void setCompletedRequests(int completedRequests) {
		this.completedRequests = completedRequests;
	}
	public int getPendingRequests() {
		return pendingRequests;
	}
	public void setPendingRequests(int pendingRequests) {
		this.pendingRequests = pendingRequests;
	}
	public double getLastRequestTime() {
		return lastRequestTime;
	}
	public void setLastRequestTime(double lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public long getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	} 
	
	
	
	
}
