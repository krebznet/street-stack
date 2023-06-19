package com.dunkware.trade.service.beach.protocol.play;

import java.util.ArrayList;
import java.util.List;

public class Play {

	private String side; 
	
	private String stream = "us_equity";
	private String signal; 
	private String name; 
	private double allocatedCapital;
	private double tradeAllocation; 
	
	// Entry Strategy 
	private PlayOrderType entryType; 
	private int entryInterval;
	private int entryOffsetActivation; 
	private double entryOffset;
	private boolean enableEntryTimeout = false; 
	private int entryTimeout; 
	private String entryTargetPrice;
	
	private boolean enableActiveTradeLimit = false; 
	private int activeTradeLimit = -1;
	private boolean enableActiveSymbolLimit = false; 
	private int activeSymbolLimit = -1; 
	private boolean enableStopLoss = false;
	private double stopLoss = -1;
	
	
	// Exit Order Type 
	private PlayOrderType exitType; 
	private int exitTargetPrice;
	private int exitInterval; 
	private double exitOffset;
	private int exitOffsetInterval;
	
	private List<PlayExitTrigger> exitTriggers = new ArrayList<PlayExitTrigger>();
	
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
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
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public double getTradeAllocation() {
		return tradeAllocation;
	}
	public void setTradeAllocation(double tradeAllocation) {
		this.tradeAllocation = tradeAllocation;
	}
	public PlayOrderType getEntryType() {
		return entryType;
	}
	public void setEntryType(PlayOrderType entryType) {
		this.entryType = entryType;
	}
	public int getEntryInterval() {
		return entryInterval;
	}
	public void setEntryInterval(int entryInterval) {
		this.entryInterval = entryInterval;
	}
	public int getEntryOffsetActivation() {
		return entryOffsetActivation;
	}
	public void setEntryOffsetActivation(int entryOffsetActivation) {
		this.entryOffsetActivation = entryOffsetActivation;
	}
	public double getEntryOffset() {
		return entryOffset;
	}
	public void setEntryOffset(double entryOffset) {
		this.entryOffset = entryOffset;
	}
	public String getEntryTargetPrice() {
		return entryTargetPrice;
	}
	public void setEntryTargetPrice(String entryTargetPrice) {
		this.entryTargetPrice = entryTargetPrice;
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
	public double getStopLoss() {
		return stopLoss;
	}
	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}
	public PlayOrderType getExitType() {
		return exitType;
	}
	public void setExitType(PlayOrderType exitType) {
		this.exitType = exitType;
	}
	public int getExitTargetPrice() {
		return exitTargetPrice;
	}
	public void setExitTargetPrice(int exitTargetPrice) {
		this.exitTargetPrice = exitTargetPrice;
	}
	public int getExitInterval() {
		return exitInterval;
	}
	public void setExitInterval(int exitInterval) {
		this.exitInterval = exitInterval;
	}
	public double getExitOffset() {
		return exitOffset;
	}
	public void setExitOffset(double exitOffset) {
		this.exitOffset = exitOffset;
	}
	public int getExitOffsetInterval() {
		return exitOffsetInterval;
	}
	public void setExitOffsetInterval(int exitOffsetInterval) {
		this.exitOffsetInterval = exitOffsetInterval;
	}
	public List<PlayExitTrigger> getExitTriggers() {
		return exitTriggers;
	}
	public void setExitTriggers(List<PlayExitTrigger> exitTriggers) {
		this.exitTriggers = exitTriggers;
	}
	public boolean isEnableEntryTimeout() {
		return enableEntryTimeout;
	}
	public void setEnableEntryTimeout(boolean enableEntryTimeout) {
		this.enableEntryTimeout = enableEntryTimeout;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	
	
	
	
	
	
}
