package com.dunkware.trade.service.beach.protocol.trade.spec;

public class BeachSystemSpec {
	
	private BeachSessionStatus status; 
	private String name; 
	private String account; 
	private double activeCapital; 
	private int activeTradeCount;
	private double uplAmount;
	private int closedTradeCount;
	
	public BeachSessionStatus getStatus() {
		return status;
	}
	public void setStatus(BeachSessionStatus status) {
		this.status = status;
	}
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
	public double getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(double activeCapital) {
		this.activeCapital = activeCapital;
	}
	public int getActiveTradeCount() {
		return activeTradeCount;
	}
	public void setActiveTradeCount(int activeTradeCount) {
		this.activeTradeCount = activeTradeCount;
	}
	public double getUplAmount() {
		return uplAmount;
	}
	public void setUplAmount(double uplAmount) {
		this.uplAmount = uplAmount;
	}
	public int getClosedTradeCount() {
		return closedTradeCount;
	}
	public void setClosedTradeCount(int closedTradeCount) {
		this.closedTradeCount = closedTradeCount;
	}
	
	
	
	

}
