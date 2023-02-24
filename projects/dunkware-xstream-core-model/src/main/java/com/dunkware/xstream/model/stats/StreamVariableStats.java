package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class StreamVariableStats {
	
	private int varId; 
	private Number high = null;
	private LocalDateTime hight; 
	private LocalDateTime lowt;
	private Number low = null;
	private int values;
	
	public Number getHigh() {
		return high;
	}
	public void setHigh(Number high) {
		this.high = high;
	}
	public LocalDateTime getHight() {
		return hight;
	}
	public void setHight(LocalDateTime hight) {
		this.hight = hight;
	}
	public LocalDateTime getLowt() {
		return lowt;
	}
	public void setLowt(LocalDateTime lowt) {
		this.lowt = lowt;
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
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
	
	
	
	
	
	
	
	
	

	

	
	

	
}
