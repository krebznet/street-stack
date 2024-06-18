package com.dunkware.xstream.model.snapshot;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.utils.core.time.DunkTime;

public class SnapshotEntity {
	
	private int stream; 
	private long timestamp; 
	private int entity; 
	private Map<Integer,Number> numericVars = new HashMap<Integer,Number>();
	private Map<Integer,String> stringVars = new HashMap<Integer,String>();
	
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
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Map<Integer, Number> getNumericVars() {
		return numericVars;
	}
	public void setNumericVars(Map<Integer, Number> numericVars) {
		this.numericVars = numericVars;
	}
	public Map<Integer, String> getStringVars() {
		return stringVars;
	}
	public void setStringVars(Map<Integer, String> stringVars) {
		this.stringVars = stringVars;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append(stream).append(":").append(DunkTime.toLocaDateTimeString(timestamp)).append(":").append(entity)
				.append(":").append(numericVars.toString()).toString();
		
	}
	
	
	
	
	
	
	
	
}
