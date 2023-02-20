package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class StreamVariableStats {
	
	private int varId; 
	private String varIdent;
	private Number high = null;
	private LocalDateTime highTime; 
	private LocalDateTime lowTime;
	private Number low = null;
	private int valueCount;
	
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
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
	
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	public String getVarIdent() {
		return varIdent;
	}
	public void setVarIdent(String varIdent) {
		this.varIdent = varIdent;
	}
	
	
	
	
	
	
	
	
	
	
	

	

	
	

	
}
