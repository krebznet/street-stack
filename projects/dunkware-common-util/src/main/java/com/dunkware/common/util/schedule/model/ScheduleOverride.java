package com.dunkware.common.util.schedule.model;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class ScheduleOverride {

	private DDate date; 
	private DTime startTime = null;
	private DTime stopTime = null;
	private boolean run = false;
	private String name;
	
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
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
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
}
