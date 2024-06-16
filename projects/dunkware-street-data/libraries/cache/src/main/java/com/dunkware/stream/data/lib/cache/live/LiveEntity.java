package com.dunkware.stream.data.lib.cache.live;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.liveobject.resolver.UUIDGenerator;

@REntity
public class LiveEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@RId
	private long id; 
	private String ident; 
	private Map<String,Number> variables;
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
	
	public Map<String, Number> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Number> variables) {
		this.variables = variables;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}


