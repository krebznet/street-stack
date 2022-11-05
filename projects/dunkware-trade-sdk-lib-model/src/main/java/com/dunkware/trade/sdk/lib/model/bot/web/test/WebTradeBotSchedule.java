package com.dunkware.trade.sdk.lib.model.bot.web.test;

import java.util.ArrayList;
import java.util.List;

public class WebTradeBotSchedule {

	private List<String> days = new ArrayList<String>();
	private String startTime; 
	private String stopTime; 

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	
	
	
}
