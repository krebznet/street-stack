package com.dunkwrae.trade.service.data.mock.input;

import java.time.LocalTime;

import com.dunkware.common.util.dtime.DTimeZone;

public class MockInputSchedule {
	
	private String[] days;
	private LocalTime start; 
	private LocalTime stop; 
	private DTimeZone timeZone;
	
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	public LocalTime getStart() {
		return start;
	}
	public void setStart(LocalTime start) {
		this.start = start;
	}
	public LocalTime getStop() {
		return stop;
	}
	public void setStop(LocalTime stop) {
		this.stop = stop;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	} 
	
	

}
