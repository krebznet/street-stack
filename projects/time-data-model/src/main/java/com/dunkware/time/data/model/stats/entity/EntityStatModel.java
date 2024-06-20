package com.dunkware.time.data.model.stats.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityStatModel {
	
	private int stream; 
	private LocalDate date; 
	private int entity; 
	private int element; 
	private int stat; 
	private double value; 
	private LocalTime time;
	
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	

}
