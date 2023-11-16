package com.dunkware.xstream.model.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StreamEntitySnapshot {
	
	private LocalDateTime dateTime;
	private int id;
	private String identifier; 
	private Map<Integer,StreamEntitySnapshotVar> vars = new HashMap<Integer,StreamEntitySnapshotVar>();
	
 	
	public Map<Integer, StreamEntitySnapshotVar> getVars() {
		return vars;
	}
	public void setVars(Map<Integer, StreamEntitySnapshotVar> vars) {
		this.vars = vars;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	

}
