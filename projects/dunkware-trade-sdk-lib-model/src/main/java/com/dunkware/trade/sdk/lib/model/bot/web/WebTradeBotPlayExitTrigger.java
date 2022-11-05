package com.dunkware.trade.sdk.lib.model.bot.web;

public class WebTradeBotPlayExitTrigger {
	
	private String name; 
	private String type; 
	private Number stopPercent;
	private String stopPriceType;
	private Number trailingPercent; 
	private String trailingPriceType;
	private Number timer; 
	private Number profitLossAmount;
	private Number profitLossPercent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Number getStopPercent() {
		return stopPercent;
	}
	public void setStopPercent(Number stopPercent) {
		this.stopPercent = stopPercent;
	}
	public String getStopPriceType() {
		return stopPriceType;
	}
	public void setStopPriceType(String stopPriceType) {
		this.stopPriceType = stopPriceType;
	}
	public Number getTrailingPercent() {
		return trailingPercent;
	}
	public void setTrailingPercent(Number trailingPercent) {
		this.trailingPercent = trailingPercent;
	}
	public String getTrailingPriceType() {
		return trailingPriceType;
	}
	public void setTrailingPriceType(String trailingPriceType) {
		this.trailingPriceType = trailingPriceType;
	}
	public Number getTimer() {
		return timer;
	}
	public void setTimer(Number timer) {
		this.timer = timer;
	}
	public Number getProfitLossAmount() {
		return profitLossAmount;
	}
	public void setProfitLossAmount(Number profitLossAmount) {
		this.profitLossAmount = profitLossAmount;
	}
	public Number getProfitLossPercent() {
		return profitLossPercent;
	}
	public void setProfitLossPercent(Number profitLossPercent) {
		this.profitLossPercent = profitLossPercent;
	}
	
	
}
