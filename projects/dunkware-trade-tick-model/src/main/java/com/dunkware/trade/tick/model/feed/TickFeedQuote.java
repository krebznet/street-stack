package com.dunkware.trade.tick.model.feed;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DDateTime;

public class TickFeedQuote {

	private String symbol; 
	private volatile double askPrice; 
	private volatile double bidPrice; 
	private volatile int askSize; 
	private volatile int bidSize; 
	private DDateTime time;

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
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
	public int getAskSize() {
		return askSize;
	}
	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}
	public int getBidSize() {
		return bidSize;
	}
	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}
	public DDateTime getTime() {
		return time;
	}
	public void setTime(DDateTime time) {
		this.time = time;
	}
	
	
	
	
}
