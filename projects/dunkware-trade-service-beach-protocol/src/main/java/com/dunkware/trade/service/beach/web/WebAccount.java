package com.dunkware.trade.service.beach.web;

public class WebAccount {

	private int id; 
	private String account; 
	private String broker; 
	private Number allocatedCapital;
	private Number activeCapital; 
	private Number unreazliedPL; 
	private Number realizedPL;
	
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
	public Number getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(Number allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public Number getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(Number activeCapital) {
		this.activeCapital = activeCapital;
	}
	public Number getUnreazliedPL() {
		return unreazliedPL;
	}
	public void setUnreazliedPL(Number unreazliedPL) {
		this.unreazliedPL = unreazliedPL;
	}
	public Number getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(Number realizedPL) {
		this.realizedPL = realizedPL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	
	
	
}
