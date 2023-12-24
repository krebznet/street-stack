package com.dunkware.xstream.model.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EntitySnapshot {
	
	private int stream; 
	private LocalDateTime dateTime; 
	private int entity; 
	private Map<String,String> vars = new HashMap<String,String>();
	
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public Map<String, String> getVars() {
		return vars;
	}
	public void setVars(Map<String, String> vars) {
		this.vars = vars;
	}
	
	
	
}
