package com.dunkware.trade.service.data.model.signals;

import java.time.LocalDateTime;

public class StreamSessionSignalTypeBean {

	private int signalCount; 
	private LocalDateTime lastDateTime; 
	private int lastEntity; 
	private int signalType; 
	private int entityCount;
	
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public LocalDateTime getLastDateTime() {
		return lastDateTime;
	}
	public void setLastDateTime(LocalDateTime lastDateTime) {
		this.lastDateTime = lastDateTime;
	}
	public int getLastEntity() {
		return lastEntity;
	}
	public void setLastEntity(int lastEntity) {
		this.lastEntity = lastEntity;
	}
	public int getSignalType() {
		return signalType;
	}
	public void setSignalType(int signalType) {
		this.signalType = signalType;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	} 
	
	
	
}
