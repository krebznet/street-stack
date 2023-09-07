package com.dunkware.xstream.model.stats.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.dunkware.common.util.time.DunkTime;

public class EntityStat {
	
	private int stat; 
	private LocalDate date; 
	private Number value; 
	private int element;
	private int entity;
	private LocalTime time;
	
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

	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	
	
	
	
	
	

}
