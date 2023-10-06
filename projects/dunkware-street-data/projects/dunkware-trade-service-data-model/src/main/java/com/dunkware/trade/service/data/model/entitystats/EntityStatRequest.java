package com.dunkware.trade.service.data.model.entitystats;

import java.time.LocalDate;

public class EntityStatRequest {
	
	private LocalDate date; 
	private int entity; 
	private int element; 
	private int stat;
	
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
	
	

}
