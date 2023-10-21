package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachPlayBean extends ObservableBean  {

	private String name; 
	private String account; 
	private int accountId; 
	private int tradeCount;
	
	private String status;
	private double activeCapital; 
	private int activeTrades; 
	private int closedTrades; 
	private String exception; 
	private double upl;
	private double rpl;
	
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public double getRpl() {
		return rpl;
	}
	public void setRpl(double rpl) {
		this.rpl = rpl;
	} 
	
	
	
	
	
	
	
	
	
	
}
