package com.dunkware.trade.tick.provider.polygon.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonQuote {
	
	@JsonProperty(value = "ev")
	private String event; 
	@JsonProperty(value = "sym")
	private String symbol; 
	@JsonProperty(value = "bs")
	private int bidSize; 
	@JsonProperty(value = "bp")
	private double bidPrice; 
	@JsonProperty(value = "ap")
	private double askPrice; 
	@JsonProperty(value = "as")
	private int askSize; 
	@JsonProperty(value = "t")
	private long time; 
	@JsonProperty(value = "z")
	private int tape; 
	@JsonProperty(value = "c")
	private int condition; 
	@JsonProperty(value = "ax")	
	private int askExchange; 
	@JsonProperty(value = "bx")
	private int bidExchange;
	@JsonProperty(value = "q")
	private int unknown; 
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getBidSize() {
		return bidSize;
	}
	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}
	public int getAskSize() {
		return askSize;
	}
	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getTape() {
		return tape;
	}
	public void setTape(int tape) {
		this.tape = tape;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getAskExchange() {
		return askExchange;
	}
	public void setAskExchange(int askExchange) {
		this.askExchange = askExchange;
	}
	public int getBidExchange() {
		return bidExchange;
	}
	public void setBidExchange(int bidExchange) {
		this.bidExchange = bidExchange;
	}
	public int getUnknown() {
		return unknown;
	}
	public void setUnknown(int unknown) {
		this.unknown = unknown;
	} 
	
	
	
	

}
