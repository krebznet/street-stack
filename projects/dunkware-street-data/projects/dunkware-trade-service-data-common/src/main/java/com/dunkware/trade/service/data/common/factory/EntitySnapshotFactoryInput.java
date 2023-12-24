package com.dunkware.trade.service.data.common.factory;

import java.time.LocalDateTime;

public class EntitySnapshotFactoryInput {

	private int entityCount; 
	private int varCount; 
	private LocalDateTime startDateTime; 
	private long durationSeconds;
	private int stream; 
	
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public int getVarCount() {
		return varCount;
	}
	public void setVarCount(int varCount) {
		this.varCount = varCount;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public long getDurationSeconds() {
		return durationSeconds;
	}
	public void setDurationSeconds(long durationSeconds) {
		this.durationSeconds = durationSeconds;
	}
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	
	
	
	
}
