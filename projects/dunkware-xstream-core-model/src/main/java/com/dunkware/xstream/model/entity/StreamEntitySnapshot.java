package com.dunkware.xstream.model.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StreamEntitySnapshot {
	
	private LocalDateTime dateTime;
	private int entityId; 
	private Map<Integer,Object> vars = new HashMap<Integer,Object>();
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public Map<Integer, Object> getVars() {
		return vars;
	}
	public void setVars(Map<Integer, Object> vars) {
		this.vars = vars;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	

}
