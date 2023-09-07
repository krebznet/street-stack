package com.dunkware.xstream.model.stats.entity;

import java.time.LocalDate;

public class EntityStatQuery {

	private LocalDate from; 
	private LocalDate to; 
	private int entity; 
	private int stat;
	
	public LocalDate getFrom() {
		return from;
	}
	public void setFrom(LocalDate from) {
		this.from = from;
	}
	public LocalDate getTo() {
		return to;
	}
	public void setTo(LocalDate to) {
		this.to = to;
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
	
	
	
}
