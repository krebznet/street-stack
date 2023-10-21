package com.dunkware.trade.sdk.core.model.broker;


/**
 * Okay this is what we need here. 
 * @author duncankrebs
 *
 */
public class BrokerAccountSpec {
	
	private String name; 
	private String broker; 
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
