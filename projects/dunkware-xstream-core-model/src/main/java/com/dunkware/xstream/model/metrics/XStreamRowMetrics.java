package com.dunkware.xstream.model.metrics;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class XStreamRowMetrics {
	
	private String rowId; 
	private int varCount; 
	private long tickCount; 
	private LocalTime streamTimeCreate;
	private LocalTime realTimeCreate;
	private List<XStreamVarMetrics> varStats = new ArrayList<XStreamVarMetrics>();
	
	public XStreamRowMetrics() { 
		
	}
	
	public String getRowId() {
		return rowId;
	}
	
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	
	public int getVarCount() {
		return varCount;
	}
	public void setVarCount(int varCount) {
		this.varCount = varCount;
	}
	public long getTickCount() {
		return tickCount;
	}
	public void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}
	
	
	public LocalTime getStreamTimeCreate() {
		return streamTimeCreate;
	}
	public void setStreamTimeCreate(LocalTime streamTimeCreate) {
		this.streamTimeCreate = streamTimeCreate;
	}
	public LocalTime getRealTimeCreate() {
		return realTimeCreate;
	}
	public void setRealTimeCreate(LocalTime realTimeCreate) {
		this.realTimeCreate = realTimeCreate;
	}
	public List<XStreamVarMetrics> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<XStreamVarMetrics> varStats) {
		this.varStats = varStats;
	}
	
	

	
	

	
	
}
