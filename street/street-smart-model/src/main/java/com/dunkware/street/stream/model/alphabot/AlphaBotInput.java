package com.dunkware.street.stream.model.alphabot;

public class AlphaBotInput {
	
	private String name;
	private String strategy; 
	private String twsHost; 
	private int twsPort; 
	private int twsClientId;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public String getTwsHost() {
		return twsHost;
	}
	public void setTwsHost(String twsHost) {
		this.twsHost = twsHost;
	}
	public int getTwsPort() {
		return twsPort;
	}
	public void setTwsPort(int twsPort) {
		this.twsPort = twsPort;
	}
	public int getTwsClientId() {
		return twsClientId;
	}
	public void setTwsClientId(int twsClientId) {
		this.twsClientId = twsClientId;
	}
	
	

}
