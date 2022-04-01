package com.dunkware.common.tick.reactor.blueprint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class ReactoryStrategy {
	

	private String name; 
	private String tickset;

	
	
	// get this working with GSON then ... 
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTickset() {
		return tickset;
	}
	public void setTickset(String tickset) {
		this.tickset = tickset;
	}
	
	
	
	
	
	

}
