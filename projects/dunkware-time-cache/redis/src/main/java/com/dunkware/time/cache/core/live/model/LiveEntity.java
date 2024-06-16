package com.dunkware.time.cache.core.live.model;

import java.util.List;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

@REntity
public class LiveEntity {

	@RId
	private long id; 
	private String ident; 
	private List<LiveVariable> variables;
	private String timestamp; 
	
	public LiveEntity() { 
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public List<LiveVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<LiveVariable> variables) {
		this.variables = variables;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	
}

// we want variables -- meta data on the variable type -> 
// right --> 

