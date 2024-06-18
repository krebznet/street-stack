package com.dunkware.xstream.model.snapshot;

import java.time.LocalDateTime;

public class SnapshotVariable {

	private int stream; 
	private int entity;
	private int var; 
	private long timestamp; 
	private double value;
	
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public int getVar() {
		return var;
	}
	public void setVar(int var) {
		this.var = var;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
	
	
	
	
	
}
