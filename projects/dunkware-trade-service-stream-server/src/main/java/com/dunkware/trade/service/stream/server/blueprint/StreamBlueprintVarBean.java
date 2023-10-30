package com.dunkware.trade.service.stream.server.blueprint;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamBlueprintVarBean extends ObservableBean {
	
	private long id; 
	private String identifier; 
	private String group; 
	private String name; 
	private String expresion;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExpresion() {
		return expresion;
	}
	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
	
	
	
	

}
