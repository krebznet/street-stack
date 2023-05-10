package com.dunkware.trade.service.beach.protocol.spec;

public class BeachBotSpec {

	private String name; 
	private String account; 
	private String broker; 
	private Number activeCapital; 
	private Number tradedCapital; 
	private Number allocatedCapital;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public Number getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(Number activeCapital) {
		this.activeCapital = activeCapital;
	}
	public Number getTradedCapital() {
		return tradedCapital;
	}
	public void setTradedCapital(Number tradedCapital) {
		this.tradedCapital = tradedCapital;
	}
	public Number getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(Number allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	} 
	
	
	
}
