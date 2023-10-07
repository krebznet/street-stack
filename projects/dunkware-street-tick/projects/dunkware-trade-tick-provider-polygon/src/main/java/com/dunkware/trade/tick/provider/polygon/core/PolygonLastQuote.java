package com.dunkware.trade.tick.provider.polygon.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonLastQuote {
	
	@JsonProperty(value = "P")
	private double askPrice; 
	@JsonProperty(value = "p" )
	private double bidPrice;
	@JsonProperty(value = "s")
	private int bidSize;
	@JsonProperty(value = "P")
	private int askSize; 
	@JsonProperty(value = "t")
	private long timestamp;
	
	public double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public int getBidSize() {
		return bidSize;
	}
	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}
	public int getAskSize() {
		return askSize;
	}
	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	} 
	
	
	

}
