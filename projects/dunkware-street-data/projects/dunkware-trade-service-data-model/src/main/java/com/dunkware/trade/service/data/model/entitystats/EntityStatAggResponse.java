package com.dunkware.trade.service.data.model.entitystats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EntityStatAggResponse {

	// did we have enough days of data? 
	private boolean resolved; 
	private Number value; 
	private LocalTime time;
	private LocalDate date; 
	
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
