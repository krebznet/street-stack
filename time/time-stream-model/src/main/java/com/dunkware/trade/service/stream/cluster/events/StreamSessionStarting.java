package com.dunkware.trade.service.stream.cluster.events;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StreamSessionStarting {
	
	private String identifier; 
	private long id; 
	private long sessionId; 
	private LocalDateTime startingTime; 
	
	private Map<String,String> properties = new HashMap<String,String>();
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
