package com.dunkware.trade.service.data.model.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class EntitySignal {
	
	private int type;
	private int entity;
	LocalDate date; 
	LocalTime time; 
	
	private Map<Integer, Number> varValues = new HashMap<Integer,Number>();

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Map<Integer, Number> getVarValues() {
		return varValues;
	}
	public void setVarValues(Map<Integer, Number> varValues) {
		this.varValues = varValues;
	}
	
	


	
	
	
	
	
	
	
	

}
