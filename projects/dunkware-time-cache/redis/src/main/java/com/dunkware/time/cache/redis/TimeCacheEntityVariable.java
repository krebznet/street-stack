package com.dunkware.time.cache.redis;

import java.time.LocalDateTime;


public class TimeCacheEntityVariable {
	
	private int entityType; 
	private int entityId; 
	private int varId; 
	private LocalDateTime timestamp; 
	
	private double value;
	public int getEntityType() {
		return entityType;
	}
	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public int getVarId() {
		return varId;
	}
	public void setVarId(int varId) {
		this.varId = varId;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	} 
	
	

}
