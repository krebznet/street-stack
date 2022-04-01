package com.dunkware.trade.service.data.json.data.stream;

import com.dunkware.trade.service.data.json.data.stream.session.StreamSessionStats;

public class StreamStats {
	
	private String name; 
	private boolean running; 
	
	private StreamSessionStats sessionStats;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public StreamSessionStats getSessionStats() {
		return sessionStats;
	}

	public void setSessionStats(StreamSessionStats sessionStats) {
		this.sessionStats = sessionStats;
	}
	
	
	

}
