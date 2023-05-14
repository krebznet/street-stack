package com.dunkware.trade.service.beach.server.runtime;

public class BeachPlayBean {

	private String name; 
	private String account; 
	private double activeCapital; 
	private int activeTrades; 
	private int closedTrades; 
	private double upl;
	
	
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
	public int getActiveTrades() {
		return activeTrades;
	}
	public void setActiveTrades(int activeTrades) {
		this.activeTrades = activeTrades;
	}
	public int getClosedTrades() {
		return closedTrades;
	}
	public void setClosedTrades(int closedTrades) {
		this.closedTrades = closedTrades;
	}
	public double getUpl() {
		return upl;
	}
	public void setUpl(double upl) {
		this.upl = upl;
	} 
	
	
}
