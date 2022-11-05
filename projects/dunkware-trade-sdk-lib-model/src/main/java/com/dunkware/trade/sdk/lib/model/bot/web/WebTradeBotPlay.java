package com.dunkware.trade.sdk.lib.model.bot.web;

import java.util.ArrayList;
import java.util.List;

public class WebTradeBotPlay  {
	
	private String name; 
	private String entryType; 
	private Number entryTimeout;
	private Number entryLimitPercent; 
	private Number entiryLimitPriceType; 
	private Number entryLimitChaseInterval;
	private List<WebTradeBotPlayExitTrigger> exitTriggers = new ArrayList<WebTradeBotPlayExitTrigger>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public Number getEntryTimeout() {
		return entryTimeout;
	}
	public void setEntryTimeout(Number entryTimeout) {
		this.entryTimeout = entryTimeout;
	}
	public Number getEntryLimitPercent() {
		return entryLimitPercent;
	}
	public void setEntryLimitPercent(Number entryLimitPercent) {
		this.entryLimitPercent = entryLimitPercent;
	}
	public Number getEntiryLimitPriceType() {
		return entiryLimitPriceType;
	}
	public void setEntiryLimitPriceType(Number entiryLimitPriceType) {
		this.entiryLimitPriceType = entiryLimitPriceType;
	}
	public Number getEntryLimitChaseInterval() {
		return entryLimitChaseInterval;
	}
	public void setEntryLimitChaseInterval(Number entryLimitChaseInterval) {
		this.entryLimitChaseInterval = entryLimitChaseInterval;
	}
	public List<WebTradeBotPlayExitTrigger> getExitTriggers() {
		return exitTriggers;
	}
	public void setExitTriggers(List<WebTradeBotPlayExitTrigger> exitTriggers) {
		this.exitTriggers = exitTriggers;
	}
	
	

}
