package com.dunkware.trade.service.beach.server.session;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachSessionBean extends ObservableBean  {

	private String status; 
	private double activeCapital; 
	private double tradedCapital; 
	private int activeTrades; 
	private int closedTrades; 
	private int activeOrders; 
	private double unrealizedPL; 
	private double realizedPL;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getActiveCapital() {
		return activeCapital;
	}
	public void setActiveCapital(double activeCapital) {
		this.activeCapital = activeCapital;
	}
	public double getTradedCapital() {
		return tradedCapital;
	}
	public void setTradedCapital(double tradedCapital) {
		this.tradedCapital = tradedCapital;
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
	public int getActiveOrders() {
		return activeOrders;
	}
	public void setActiveOrders(int activeOrders) {
		this.activeOrders = activeOrders;
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
