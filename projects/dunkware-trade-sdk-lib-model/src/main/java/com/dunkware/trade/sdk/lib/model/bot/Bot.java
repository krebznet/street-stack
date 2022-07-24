package com.dunkware.trade.sdk.lib.model.bot;

import java.util.ArrayList;
import java.util.List;

public class Bot {
	
	private String name; 
	private String account; 
	private Number allocatedCapital; 
	private List<BotPlay> plays = new ArrayList<BotPlay>();
	private boolean enabled; 
	private Number activeTradeLimit; 
	private Number tradeThrottle;
	
	
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
	public void setAllocatedCapital(Number allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public List<BotPlay> getPlays() {
		return plays;
	}
	public void setPlays(List<BotPlay> plays) {
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
	public void setActiveTradeLimit(Number activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public Number getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(Number tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	} 
	
	
	

}
