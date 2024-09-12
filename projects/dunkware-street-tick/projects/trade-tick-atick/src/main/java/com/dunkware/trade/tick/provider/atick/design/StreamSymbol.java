package com.dunkware.trade.tick.provider.atick.design;

public class StreamSymbol {
	
	private volatile double last; 
	private volatile double askPrice; 
	private volatile int askSize; 
	private volatile int bidSize; 
	private volatile double bidPrice; 
	private volatile long volume; 
	private volatile int tradeCount; 
	private volatile int lastSize;
	
	
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
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
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getLastSize() {
		return lastSize;
	}
	public void setLastSize(int lastSize) {
		this.lastSize = lastSize;
	} 
	
	

}
