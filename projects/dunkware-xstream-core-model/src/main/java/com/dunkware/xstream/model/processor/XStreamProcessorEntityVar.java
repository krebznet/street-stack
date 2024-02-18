package com.dunkware.xstream.model.processor;

import java.time.LocalTime;

public class XStreamProcessorEntityVar {
	
	private int varId;  
	private LocalTime firstUpdateTime; 
	private LocalTime lastUpdateTime; 
	private int updateCount; 
	private boolean numeric; 
	private Object lastValue;
	
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
	public LocalTime getFirstUpdateTime() {
		return firstUpdateTime;
	}
	public void setFirstUpdateTime(LocalTime firstUpdateTime) {
		this.firstUpdateTime = firstUpdateTime;
	}
	public LocalTime getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(LocalTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}
	public boolean isNumeric() {
		return numeric;
	}
	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}
	public Object getLastValue() {
		return lastValue;
	}
	public void setLastValue(Object lastValue) {
		this.lastValue = lastValue;
	} 
	
	

}
