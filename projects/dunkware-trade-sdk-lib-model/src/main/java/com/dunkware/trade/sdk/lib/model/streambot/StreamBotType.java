package com.dunkware.trade.sdk.lib.model.streambot;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public class StreamBotType extends SystemType {
	
	private String name; 
	private String account; 
	private double allocatedCapital; 
	private List<StreamBotPlayType> plays = new ArrayList<StreamBotPlayType>();
	private boolean enabled; 
	private int activeTradeLimit; 
	private int tradeThrottle;
	
	
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
	public Number getAllocatedCapital() {
		return allocatedCapital;
	}
	
	public List<StreamBotPlayType> getPlays() {
		return plays;
	}
	public void setPlays(List<StreamBotPlayType> plays) {
		this.plays = plays;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Number getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public int getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(int tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public void setActiveTradeLimit(int activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	
	
	

	
	
	

}
