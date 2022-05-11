package com.dunkware.trade.tick.service.protocol.feed;

public class TickFeedStats {
	
	private String sessionId; 
	private int tickerCount; 
	private int tickCount;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getTickerCount() {
		return tickerCount;
	}
	public void setTickerCount(int tickerCount) {
		this.tickerCount = tickerCount;
	}
	public int getTickCount() {
		return tickCount;
	}
	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	} 
	
	

}
