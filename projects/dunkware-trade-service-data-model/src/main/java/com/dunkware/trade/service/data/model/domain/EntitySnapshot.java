package com.dunkware.trade.service.data.model.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EntitySnapshot {
	
	private int StreamId; 
	private LocalDateTime timestamp;
	private int entityId; 
	private Map<Integer,Object> vars = new HashMap<Integer,Object>();
	
	public int getStreamId() {
		return StreamId;
	}
	public void setStreamId(int streamId) {
		StreamId = streamId;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
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
	
	

}
