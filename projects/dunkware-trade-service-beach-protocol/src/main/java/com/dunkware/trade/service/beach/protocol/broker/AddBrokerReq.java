package com.dunkware.trade.service.beach.protocol.broker;

public class AddBrokerReq {
	
	private String name; 
	private String host; 
	private int port;
	private int clientId; 
	
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
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	} 
	
	

}
