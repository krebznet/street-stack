package com.dunkware.trade.service.beach.protocol.trade.pool;

public class BeachPoolAddReq {
	
	private String name; 
	private String broker; 
	private String account; 
	
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
