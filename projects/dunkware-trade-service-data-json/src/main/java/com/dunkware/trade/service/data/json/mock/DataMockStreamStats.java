package com.dunkware.trade.service.data.json.mock;

import com.dunkware.common.util.dtime.DTime;

public class DataMockStreamStats {
	
	private String identifier;
	private boolean running = false;
	private DTime startTime = null; 
	private DTime stopTime = null;
	private String days; 
	
	
	private DataMockStreamSessionStats session = null;


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public DTime getStartTime() {
		return startTime;
	}


	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}


	public DTime getStopTime() {
		return stopTime;
	}


	public void setStopTime(DTime stopTime) {
		this.stopTime = stopTime;
	}


	public String getDays() {
		return days;
	}


	public void setDays(String days) {
		this.days = days;
	}


	public DataMockStreamSessionStats getSession() {
		return session;
	}


	public void setSession(DataMockStreamSessionStats session) {
		this.session = session;
	}


	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	} 
	
	
	
	

}
