package com.dunkware.trade.sdk.tws.model;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;

public class TwsBrokerType implements BrokerType {

	private String host; 
	private int port; 
	private int connectionId;
	private String identifier;
	
	public TwsBrokerType() { 
		
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
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	} 
	
	
}
