package com.dunkware.trade.domain.broker;

import java.util.Map;

public class BrokerModel {

	private boolean live; 
	private int connectId;
	private String name; 
	private String host; 
	private int port; 
	private String type;
	private Map<String,Object> properties;
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getConnectId() {
		return connectId;
	}
	public void setConnectId(int connectId) {
		this.connectId = connectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	
	
	
}
