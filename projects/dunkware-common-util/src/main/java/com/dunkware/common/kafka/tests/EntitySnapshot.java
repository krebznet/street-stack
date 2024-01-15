package com.dunkware.common.kafka.tests;

import java.util.HashMap;
import java.util.Map;

public class EntitySnapshot {
	
	private int streamId; 
	private long timestamp;
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
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	

}
