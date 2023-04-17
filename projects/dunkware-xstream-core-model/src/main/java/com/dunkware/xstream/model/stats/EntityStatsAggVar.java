package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class EntityStatsAggVar {

	private int id;
	private String ident;
	private Number high = null;
	private LocalDateTime highDateTime;
	private LocalDateTime lowDateTime;
	private Number low = null;
	private int sessionCount;
	private long valueCount;
	
	
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
	public LocalDateTime getHighDateTime() {
		return highDateTime;
	}
	public void setHighDateTime(LocalDateTime highDateTime) {
		this.highDateTime = highDateTime;
	}
	public LocalDateTime getLowDateTime() {
		return lowDateTime;
	}
	public void setLowDateTime(LocalDateTime lowDateTime) {
		this.lowDateTime = lowDateTime;
	}
	public Number getLow() {
		return low;
	}
	public void setLow(Number low) {
		this.low = low;
	}

	public long getValueCount() {
		return valueCount;
	}
	public void setValueCount(long valueCount) {
		this.valueCount = valueCount;
	}
	public int getSessionCount() {
		return sessionCount;
	}
	public void setSessionCount(int sessionCount) {
		this.sessionCount = sessionCount;
	}
	
	
	
	
	

}
