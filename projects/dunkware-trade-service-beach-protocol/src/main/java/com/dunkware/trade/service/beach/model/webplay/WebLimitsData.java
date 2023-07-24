package com.dunkware.trade.service.beach.model.webplay;

public class WebLimitsData {
	
	private boolean enableActiveTradeLimit  = false; 
	private boolean enableActiveSymbolLimit = false; 
	private boolean enableStopLoss = false; 
	private int activeTradeLimit; 
	private int activeSymbolLimit; 
	private double stopLoss;
	
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
	public double getStopLoss() {
		return stopLoss;
	}
	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	} 
	
	
	

}
