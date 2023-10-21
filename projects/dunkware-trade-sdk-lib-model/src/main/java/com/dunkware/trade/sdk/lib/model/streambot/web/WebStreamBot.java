package com.dunkware.trade.sdk.lib.model.streambot.web;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.lib.model.streambot.web.test.WebTradeBotSchedule;

public class WebStreamBot {
	
	private String name; 
	private String account; 
	private Number allocatedCapital; 
	private boolean enabled; 
	private Number activeTradeLimit; 
	private Number activeSymbolLimit; 
	private Number tradeThrottle;
	private Number symbolThrottle;
	private String startTime = null;
	private String stopTime = null;
	private WebTradeBotSchedule schedule = null;
	private List<WebStreamBotPlay> plays = new ArrayList<WebStreamBotPlay>();
	
	
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
	public Number getActiveSymbolLimit() {
		return activeSymbolLimit;
	}
	public void setActiveSymbolLimit(Number activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}
	public Number getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(Number tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	public Number getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(Number symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public List<WebStreamBotPlay> getPlays() {
		return plays;
	}
	public void setPlays(List<WebStreamBotPlay> plays) {
		this.plays = plays;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public WebTradeBotSchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(WebTradeBotSchedule schedule) {
		this.schedule = schedule;
	}
	
	
	
	
	
	
	
	

}
