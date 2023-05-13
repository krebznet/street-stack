package com.dunkware.trade.service.beach.protocol.broker;

import java.util.Map;

public class BeachBrokerModel {
	
	private BeachBrokerType type; // tws
	private String name; 
	private Map<String,Object> params;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public BeachBrokerType getType() {
		return type;
	}
	public void setType(BeachBrokerType type) {
		this.type = type;
	}
	
	
	
	

}
// WebBrokerInsert