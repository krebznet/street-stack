package com.dunkware.xstream.model.stats;

import java.util.ArrayList;
import java.util.List;

public class SessionStats {

	private String sessionId; 
	private String streamIdent; 
	private List<EntityStats> entityStats = new ArrayList<EntityStats>();
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public List<EntityStats> getEntityStats() {
		return entityStats;
	}
	public void setEntityStats(List<EntityStats> entityStats) {
		this.entityStats = entityStats;
	}
	
	
}
