package com.dunkware.xstream.api;

import java.time.LocalTime;
import java.util.Map;

public class XStreamRowSnapshot {
	
	public LocalTime time;
	private String rowId; 
	private int numericId;
	private Map<String,Object> varMap;
	
	public XStreamRowSnapshot(String rowId, int numericId, LocalTime time, Map<String,Object> varMap) { 
		this.time = time; 
		this.rowId = rowId; 
		this.numericId = numericId;
		this.varMap = varMap;
	}
	
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public Map<String, Object> getVarMap() {
		return varMap;
	}
	public void setVarMap(Map<String, Object> varMap) {
		this.varMap = varMap;
	}

	public int getNumericId() {
		return numericId;
	}

	public void setNumericId(int numericId) {
		this.numericId = numericId;
	}
	
	
	
	

}
