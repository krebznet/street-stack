package com.dunkware.trade.sdk.core.model.broker;

public class BrokerSpec {

	private String identifier; 
	private BrokerStatus status;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public BrokerStatus getStatus() {
		return status;
	}
	public void setStatus(BrokerStatus status) {
		this.status = status;
	} 
	
	
	
}
