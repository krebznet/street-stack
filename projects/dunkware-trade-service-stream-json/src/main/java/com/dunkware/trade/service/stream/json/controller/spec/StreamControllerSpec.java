package com.dunkware.trade.service.stream.json.controller.spec;

import java.time.LocalTime;

import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamControllerSpec {

	private String name; 
	private XScriptBundle bundle; 
	private String days;
	private double version;
	private String timeZone;
	private LocalTime startTime; 
	private LocalTime stopTime; 
	
	private String tickers;
	private String dataTicks;
	
	private boolean schedule = true;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public XScriptBundle getBundle() {
		return bundle;
	}
	public void setBundle(XScriptBundle bundle) {
		this.bundle = bundle;
	}
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public String getTickers() {
		return tickers;
	}
	public void setTickers(String tickers) {
		this.tickers = tickers;
	}
	public String getDataTicks() {
		return dataTicks;
	}
	public void setDataTicks(String dataTicks) {
		this.dataTicks = dataTicks;
	}
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}

	
	
	
	
	
	
}
