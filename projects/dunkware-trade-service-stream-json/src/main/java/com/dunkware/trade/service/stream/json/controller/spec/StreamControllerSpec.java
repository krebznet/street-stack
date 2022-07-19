package com.dunkware.trade.service.stream.json.controller.spec;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class StreamControllerSpec {

	private String name; 
	private XScriptBundle bundle; 
	private String days;
	private double version;
	private DTimeZone timeZone;
	private DTime startTime; 
	private DTime stopTime; 
	
	private String tickers;
	private String dataTicks;
	private DCountry country; 
	
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
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
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
	public DCountry getCountry() {
		return country;
	}
	public void setCountry(DCountry country) {
		this.country = country;
	}
	
	
	
	
	
	
	
	
}
