package com.dunkware.trade.tick.model.instrument;

import com.dunkware.common.util.dtime.DDateTime;

public class EquityQuote {
	
	private String event; 
	private String symbol; 
	private int bidSize; 
	private double bidPrice; 
	private double askPrice; 
	private int askSize; 
	private double lastPrice; 
	private int tradeCount; 
	private int volume; 
	private DDateTime lastQuote; 
	private DDateTime lastTrade; 
	
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
	public double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public DDateTime getLastQuote() {
		return lastQuote;
	}
	public void setLastQuote(DDateTime lastQuote) {
		this.lastQuote = lastQuote;
	}
	public DDateTime getLastTrade() {
		return lastTrade;
	}
	public void setLastTrade(DDateTime lastTrade) {
		this.lastTrade = lastTrade;
	}
	
	

}
