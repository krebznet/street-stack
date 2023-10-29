package com.dunkware.trade.service.beach.server.broker;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachBrokerAccountBean extends ObservableBean {
	
	private String brokerIdentifier; 
	private long brokerId; 
	private String identifier;
	
	public String getBrokerIdentifier() {
		return brokerIdentifier;
	}
	public void setBrokerIdentifier(String brokerIdentifier) {
		this.brokerIdentifier = brokerIdentifier;
	}
	public long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(long brokerId) {
		this.brokerId = brokerId;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	} 
	
	

}
