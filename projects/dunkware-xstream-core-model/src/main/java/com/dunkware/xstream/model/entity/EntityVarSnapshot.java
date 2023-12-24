package com.dunkware.xstream.model.entity;

import java.time.LocalDateTime;

import com.dunkware.common.util.time.DunkTime;

public class EntityVarSnapshot {

	private int id;
	private String ident;
	private LocalDateTime dateTime; 
	private double value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(DunkTime.format(dateTime, DunkTime.YYMMDDHHMMSS));
		b.append(":");
		b.append(id);
		b.append(":");
		b.append(ident);
		b.append(":");
		b.append(value);
		
		
		return b.toString();
	} 
	
	
	
	
}
