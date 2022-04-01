package com.dunkware.trade.tick.model.provider;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude()
public class TickProviderSpec {

	private String name; 
	private Map<String,Object> properties = new HashMap<String,Object>();
	private String type;
	
	public TickProviderSpec() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	

	
	

}
