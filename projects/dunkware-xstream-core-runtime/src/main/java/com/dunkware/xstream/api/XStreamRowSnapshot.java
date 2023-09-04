package com.dunkware.xstream.api;

import java.util.Map;

import com.dunkware.common.util.dtime.DTime;

public class XStreamRowSnapshot {
	
	public DTime time;
	private String rowId; 
	private int numericId;
	private Map<String,Object> varMap;
	
	public XStreamRowSnapshot(String rowId, int numericId, DTime time, Map<String,Object> varMap) { 
		this.time = time; 
		this.rowId = rowId; 
		this.numericId = numericId;
		this.varMap = varMap;
	}
	
	public DTime getTime() {
		return time;
	}
	public void setTime(DTime time) {
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
