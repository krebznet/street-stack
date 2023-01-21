package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDateTime;

public class SessionEntityVarStats {
	
	private long id; 
	private int varId; 
	private String varIdent; 
	private double high; 
	private double low; 
	private LocalDateTime highTime; 
	private LocalDateTime lowTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
	public String getVarIdent() {
		return varIdent;
	}
	public void setVarIdent(String varIdent) {
		this.varIdent = varIdent;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
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
	
	
	
}
