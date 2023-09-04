package com.dunkware.xstream.model.stats.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityStat {
	
	private int type; 
	private LocalDate date; 
	private Number value; 
	private int target;; 
	private LocalTime time;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	} 
	
	

}
