package com.dunkware.trade.service.beach.model.system.confg;

public class BeachSystemSchedule {

	private String[] days; 
	private String startTime; 
	private String stopTIme;
	
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTIme() {
		return stopTIme;
	}
	public void setStopTIme(String stopTIme) {
		this.stopTIme = stopTIme;
	} 
	
	
}
