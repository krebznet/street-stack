package com.dunkware.trade.service.beach.protocol.trade.transport;

public class AccountTransport {
	
	private String name; 
	private String broker; 
	private String status; 
	private double marketValue; 
	private double activeCapital; 
	private int activeTradeBots; 
	private double unrealizedPL;
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
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public double getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(double activeCapital) {
		this.activeCapital = activeCapital;
	}
	public int getActiveTradeBots() {
		return activeTradeBots;
	}
	public void setActiveTradeBots(int activeTradeBots) {
		this.activeTradeBots = activeTradeBots;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	} 
	
	

}
