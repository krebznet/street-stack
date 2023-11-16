package com.dunkware.xstream.model.entity;

import java.time.LocalTime;

public class StreamEntitySnapshotVar {
	
	private int id; 
	private String identifier;
	private int updateCount; 
	private LocalTime lastUpdate; 
	private Object value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}
	public LocalTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	} 
	
	
	
	
	


}
