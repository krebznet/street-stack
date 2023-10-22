package com.dunkware.trade.service.data.model.entitystats;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityStatAgg {

	private boolean resolved; 
	private Number value; 
	private int entity;
	private int stat; 
	private LocalTime time;
	private LocalDate date; 
	private boolean error = false; 
	private String errorMesssage;
	
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
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
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
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMesssage() {
		return errorMesssage;
	}
	public void setErrorMesssage(String errorMesssage) {
		this.errorMesssage = errorMesssage;
	} 
	
	
}
