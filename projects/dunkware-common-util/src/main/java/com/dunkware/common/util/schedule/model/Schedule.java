package com.dunkware.common.util.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;

public class Schedule {
	
	private String name; 
	private List<ScheduleOverride> overrides = new ArrayList<ScheduleOverride>();
	private List<Integer> days = new ArrayList<Integer>();
	private DTime startTime; 
	private DTime stopTime; 
	private DTimeZone timeZone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ScheduleOverride> getOverrides() {
		return overrides;
	}
	public void setOverrides(List<ScheduleOverride> overrides) {
		this.overrides = overrides;
	}

	public List<Integer> getDays() {
		return days;
	}
	public void setDays(List<Integer> days) {
		this.days = days;
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
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	
	
	

}
