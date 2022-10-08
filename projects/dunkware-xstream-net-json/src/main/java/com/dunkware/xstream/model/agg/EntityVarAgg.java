package com.dunkware.xstream.model.agg;

import java.time.LocalTime;

public class EntityVarAgg {
	
	private int id; 
	private String ident; 
	private double high; 
	private LocalTime highTime; 
	private double low; 
	private LocalTime lowTime;
	
	
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
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public LocalTime getHighTime() {
		return highTime;
	}
	public void setHighTime(LocalTime highTime) {
		this.highTime = highTime;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public LocalTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(LocalTime lowTime) {
		this.lowTime = lowTime;
	} 
	
	
	 

}
