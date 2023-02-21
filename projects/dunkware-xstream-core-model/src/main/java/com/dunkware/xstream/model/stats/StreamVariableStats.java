package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class StreamVariableStats {
	
	private int id; 
	private String ident;
	private Number high = null;
	private LocalDateTime highTime; 
	private LocalDateTime lowTime;
	private Number low = null;
	private int values;
	
	
	public Number getHigh() {
		return high;
	}
	public void setHigh(Number high) {
		this.high = high;
	}
	public LocalDateTime getHighTime() {
		return highTime;
	}
	public void setHighTime(LocalDateTime highTime) {
		this.highTime = highTime;
	}
	public LocalDateTime getLowTime() {
		return lowTime;
	}
	public void setLowTime(LocalDateTime lowTime) {
		this.lowTime = lowTime;
	}
	public Number getLow() {
		return low;
	}
	public void setLow(Number low) {
		this.low = low;
	}
	
	public int getValues() {
		return values;
	}
	public void setValues(int values) {
		this.values = values;
	}
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
	
	
	
	
	
	
	
	
	
	
	

	

	
	

	
}
