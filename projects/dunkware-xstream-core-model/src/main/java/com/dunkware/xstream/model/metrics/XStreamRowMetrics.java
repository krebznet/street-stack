package com.dunkware.xstream.model.metrics;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;


public class XStreamRowMetrics {
	
	private String rowId; 
	private int varCount; 
	private long tickCount; 
	private DTime streamTimeCreate;
	private DTime realTimeCreate;
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
	
	
	public DTime getStreamTimeCreate() {
		return streamTimeCreate;
	}
	public void setStreamTimeCreate(DTime streamTimeCreate) {
		this.streamTimeCreate = streamTimeCreate;
	}
	public DTime getRealTimeCreate() {
		return realTimeCreate;
	}
	public void setRealTimeCreate(DTime realTimeCreate) {
		this.realTimeCreate = realTimeCreate;
	}
	public List<XStreamVarMetrics> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<XStreamVarMetrics> varStats) {
		this.varStats = varStats;
	}
	
	

	
	

	
	
}
