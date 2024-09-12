package com.dunkware.trade.tick.model.feed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TickFeedSubscriptionBean {
	
	private int snapshotCount;
	
	private String symbol; 
	private double lastPrice; 
	private double askPrice; 
	private int bidSize; 
	private int askSize; 
	private long volume; 
	private long trades;
	private double bidPrice; 
	
	
	private String lastUpdate;
	
	private double extendedLast;
	
	private double openPrice; 
	private double closePrice; 
	
	private int id; 
	
	
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
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
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public long getTrades() {
		return trades;
	}
	public void setTrades(long trades) {
		this.trades = trades;
	}
	
	
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getExtendedLast() {
		return extendedLast;
	}
	public void setExtendedLast(double extendedLast) {
		this.extendedLast = extendedLast;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
}
