package com.dunkware.trade.service.data.common.factory;

import java.time.LocalDateTime;

public class EntityVarSnapshotFactoryInput {

	private LocalDateTime startTime; 
	private int timeRange; 
	private int varCount;
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public int getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(int timeRange) {
		this.timeRange = timeRange;
	}
	public int getVarCount() {
		return varCount;
	}
	public void setVarCount(int varCount) {
		this.varCount = varCount;
	} 
	
	
	
	
}
