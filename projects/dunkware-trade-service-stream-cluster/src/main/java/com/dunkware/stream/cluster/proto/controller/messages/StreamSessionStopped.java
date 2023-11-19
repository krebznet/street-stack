package com.dunkware.stream.cluster.proto.controller.messages;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StreamSessionStopped {
	
	private String identifier; 
	private long id; 
	private long sessionId; 
	private LocalDateTime startTime; 
	private LocalDateTime stopTime;
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
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	

}
