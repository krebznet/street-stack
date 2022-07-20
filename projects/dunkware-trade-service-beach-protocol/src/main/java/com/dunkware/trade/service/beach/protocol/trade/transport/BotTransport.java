package com.dunkware.trade.service.beach.protocol.trade.transport;

public class BotTransport {
	
	private String name; 
	private String account; 
	private String status; 
	private int activeTrades; 
	private int closedTrades; 
	private double tradedCapital; 
	private double activeCapital; 
	private double unrealizedPL; 
	private double realizedPL;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public double getTradedCapital() {
		return tradedCapital;
	}
	public void setTradedCapital(double tradedCapital) {
		this.tradedCapital = tradedCapital;
	}
	public double getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(double activeCapital) {
		this.activeCapital = activeCapital;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public double getRealizedPL() {
		return realizedPL;
	}
	public void setRealizedPL(double realizedPL) {
		this.realizedPL = realizedPL;
	} 
	
	

}
