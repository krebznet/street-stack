package com.dunkware.trade.service.beach.protocol.trade.spec;

import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;

public class BeachBrokerSpec {

	private String name; 
	private String type; 
	private String host; 
	private int port;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	
}
