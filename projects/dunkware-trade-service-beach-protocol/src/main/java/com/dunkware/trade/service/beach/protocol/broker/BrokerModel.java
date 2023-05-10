package com.dunkware.trade.service.beach.protocol.broker;

import java.util.Map;

public class BrokerModel {
	
	private String type; // tws
	private String name; 
	private Map<String,Object> params;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	
	

}
