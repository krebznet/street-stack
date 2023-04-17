package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDateTime;

public class EntityStatsSessionDocVar {
	
	private int id;
	private String ident;
	private Number high = null;
	private Number low = null;
	private LocalDateTime highTime;
	private LocalDateTime lowTime;
	private String highTimeString;
	private String lowTimeString;
	private int valueCount;
	
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
	public Number getHigh() {
		return high;
	}
	public void setHigh(Number high) {
		this.high = high;
	}
	public Number getLow() {
		return low;
	}
	public void setLow(Number low) {
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
	
	public String getHighTimeString() {
		return highTimeString;
	}
	public void setHighTimeString(String highTimeString) {
		this.highTimeString = highTimeString;
	}
	public String getLowTimeString() {
		return lowTimeString;
	}
	public void setLowTimeString(String lowTimeString) {
		this.lowTimeString = lowTimeString;
	}
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	
	

}
