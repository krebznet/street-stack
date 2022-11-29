package com.dunkware.xstream.model.search;

import java.util.HashMap;
import java.util.Map;

public class SessionEntity {

	private String id; 
	private String identifier; 
	private Map<String,Object> variables = new HashMap<String,Object>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	
	
	
}
