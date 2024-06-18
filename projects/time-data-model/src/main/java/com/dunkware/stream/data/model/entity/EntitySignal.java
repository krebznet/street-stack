package com.dunkware.stream.data.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class EntitySignal {
	
	private int stream; 
	private int id;
	private int entity;
	private Map<Integer,Number> vars = new ConcurrentHashMap<Integer,Number>();
	private LocalDateTime timestamp;
	
	public EntitySignal() { 
		
	}
	
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEntity() {
		return entity;
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
	
	
	

}
