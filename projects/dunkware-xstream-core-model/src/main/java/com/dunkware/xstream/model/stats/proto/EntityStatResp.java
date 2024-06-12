package com.dunkware.xstream.model.stats.proto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityStatResp {
	
	private EntityStatRespType type; 
	private Number value; 
	private LocalTime time; 
	private LocalDate date; 
	private String exception;
	
	public EntityStatRespType getType() {
		return type;
	}
	public void setType(EntityStatRespType type) {
		this.type = type;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
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
