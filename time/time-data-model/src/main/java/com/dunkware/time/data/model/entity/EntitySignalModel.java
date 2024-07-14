package com.dunkware.time.data.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class EntitySignalModel {
	
	private int stream; 
	private int signal;
	private int entity;
	private Map<Integer,Number> vars = new ConcurrentHashMap<Integer,Number>();
	private LocalDateTime timestamp;
	
	public EntitySignalModel() { 
		
	}
	
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public Map<Integer, Number> getVars() {
		return vars;
	}
	public void setVars(Map<Integer, Number> vars) {
		this.vars = vars;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public int getEntity() {
		return entity;
	}

	
	
	
	


}
