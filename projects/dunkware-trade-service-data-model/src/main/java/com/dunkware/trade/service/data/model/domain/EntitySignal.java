package com.dunkware.trade.service.data.model.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EntitySignal {
	
	private int typeid;
	private int streamId;
	private int entityId;
	
	private LocalDateTime time;
	private Map<Integer,Object> vars = new HashMap<Integer,Object>();
	
	private String streamIdent;
	

	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public Map<Integer, Object> getVars() {
		return vars;
	}
	public void setVars(Map<Integer, Object> vars) {
		this.vars = vars;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	} 
	
	
	
	
	
	
	
	
	

}
