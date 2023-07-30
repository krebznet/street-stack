package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class EntityStatsSessionVar {

	private int id;
	private String ident;
	private Number high = null;
	private LocalDateTime highDateTime;
	private LocalDateTime lowDateTime;
	private Number low = null;
	private int valueCount;

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

	public int getValueCount() {
		return valueCount;
	}

	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	
	

	
	

	
	
	

	

}
