package com.dunkware.trade.service.beach.protocol.play;

import java.util.ArrayList;
import java.util.List;

public class Play {

	private String side; 
	
	private String signal; 
	private String playName; 
	private double maxCapital;
	private double tradeCapital; 
	
	// Entry Strategy 
	private PlayOrderType entryType; 
	private double entryChaseInterval;
	private int entryTimeout; 
	
	private int activeTradeLimit = -1;
	private int activeSymbolLimit = -1; 
	private int symbolThrottle = -1; 
	
	// Exit Order Type 
	private PlayOrderType exitType; 
	private int exitChaseInterval; 
	private List<PlayExitTrigger> exitTriggers = new ArrayList<PlayExitTrigger>();
	
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getPlayName() {
		return playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}
	public double getMaxCapital() {
		return maxCapital;
	}
	public void setMaxCapital(double maxCapital) {
		this.maxCapital = maxCapital;
	}
	public double getTradeCapital() {
		return tradeCapital;
	}
	public void setTradeCapital(double tradeCapital) {
		this.tradeCapital = tradeCapital;
	}
	public PlayOrderType getEntryType() {
		return entryType;
	}
	public void setEntryType(PlayOrderType entryType) {
		this.entryType = entryType;
	}
	public double getEntryChaseInterval() {
		return entryChaseInterval;
	}
	public void setEntryChaseInterval(double entryChaseInterval) {
		this.entryChaseInterval = entryChaseInterval;
	}
	public int getEntryTimeout() {
		return entryTimeout;
	}
	public void setEntryTimeout(int entryTimeout) {
		this.entryTimeout = entryTimeout;
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
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public PlayOrderType getExitType() {
		return exitType;
	}
	public void setExitType(PlayOrderType exitType) {
		this.exitType = exitType;
	}
	public int getExitChaseInterval() {
		return exitChaseInterval;
	}
	public void setExitChaseInterval(int exitChaseInterval) {
		this.exitChaseInterval = exitChaseInterval;
	}
	public List<PlayExitTrigger> getExitTriggers() {
		return exitTriggers;
	}
	public void setExitTriggers(List<PlayExitTrigger> exitTriggers) {
		this.exitTriggers = exitTriggers;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	
	
	
	
}
