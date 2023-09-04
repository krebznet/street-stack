package com.dunkware.trade.service.data.model.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EntitySnapshot {
	
	private LocalDateTime timestamp;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	

}
