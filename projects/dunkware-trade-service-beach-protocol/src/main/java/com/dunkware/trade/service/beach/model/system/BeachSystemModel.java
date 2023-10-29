package com.dunkware.trade.service.beach.model.system;

import java.util.ArrayList;
import java.util.List;

public class BeachSystemModel {
	
	private double allocatedCapital; 
	private String name; 
	private String accountId; 
	private boolean enableActiveTradeLimit  = false; 
	private boolean enableActiveSymbolLimit = false; 
	private boolean enableStopLoss = false; 
	private int activeTradeLimit; 
	private int activeSymbolLimit; 
	private boolean enableDefaultStopLoss; 
	private double stopLoss;
	
	
	private List<BeachSystemModelStrategy> strategies = new ArrayList<BeachSystemModelStrategy>();


	public double getAllocatedCapital() {
		return allocatedCapital;
	}


	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public boolean isEnableActiveTradeLimit() {
		return enableActiveTradeLimit;
	}


	public void setEnableActiveTradeLimit(boolean enableActiveTradeLimit) {
		this.enableActiveTradeLimit = enableActiveTradeLimit;
	}


	public boolean isEnableActiveSymbolLimit() {
		return enableActiveSymbolLimit;
	}


	public void setEnableActiveSymbolLimit(boolean enableActiveSymbolLimit) {
		this.enableActiveSymbolLimit = enableActiveSymbolLimit;
	}


	public boolean isEnableStopLoss() {
		return enableStopLoss;
	}


	public void setEnableStopLoss(boolean enableStopLoss) {
		this.enableStopLoss = enableStopLoss;
	}


	public int getActiveTradeLimit() {
		return activeTradeLimit;
	}


	public void setActiveTradeLimit(int activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}


	public int getActiveSymbolLimit() {
		return activeSymbolLimit;
	}


	public void setActiveSymbolLimit(int activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}


	public boolean isEnableDefaultStopLoss() {
		return enableDefaultStopLoss;
	}


	public void setEnableDefaultStopLoss(boolean enableDefaultStopLoss) {
		this.enableDefaultStopLoss = enableDefaultStopLoss;
	}


	public double getStopLoss() {
		return stopLoss;
	}


	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}


	public List<BeachSystemModelStrategy> getStrategies() {
		return strategies;
	}


	public void setStrategies(List<BeachSystemModelStrategy> strategies) {
		this.strategies = strategies;
	}
	
	
	
	
	
	

}
