package com.dunkware.trade.tick.model.feed;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DDateTime;

public class TickFeedSnapshot {
	
	private String symbol; 
	private long volume; 
	private int tradeCount; 
	private double last; 
	private double askPrice; 
	private double bidPrice; 
	private int bidSize; 
	private int askSize; 
	private long premarketVolume;
	private int premarketTradeCount;
	private double afterMarketClosePrice;
	private int afterMarketTradeCount;
	private long afterMarketVolume; 
	private double extendedHoursLastPrice; 
	
	
	private DDateTime time;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public DDateTime getTime() {
		return time;
	}

	public void setTime(DDateTime time) {
		this.time = time;
	}

	public long getPremarketVolume() {
		return premarketVolume;
	}

	public void setPremarketVolume(long premarketVolume) {
		this.premarketVolume = premarketVolume;
	}

	public int getPremarketTradeCount() {
		return premarketTradeCount;
	}

	public void setPremarketTradeCount(int premarketTradeCount) {
		this.premarketTradeCount = premarketTradeCount;
	}

	public double getAfterMarketClosePrice() {
		return afterMarketClosePrice;
	}

	public void setAfterMarketClosePrice(double afterMarketClosePrice) {
		this.afterMarketClosePrice = afterMarketClosePrice;
	}

	public int getAfterMarketTradeCount() {
		return afterMarketTradeCount;
	}

	public void setAfterMarketTradeCount(int afterMarketTradeCount) {
		this.afterMarketTradeCount = afterMarketTradeCount;
	}

	public long getAfterMarketVolume() {
		return afterMarketVolume;
	}

	public void setAfterMarketVolume(long afterMarketVolume) {
		this.afterMarketVolume = afterMarketVolume;
	}

	public double getExtendedHoursLastPrice() {
		return extendedHoursLastPrice;
	}

	public void setExtendedHoursLastPrice(double extendedHoursLastPrice) {
		this.extendedHoursLastPrice = extendedHoursLastPrice;
	}
	
	
	
	

	
	
	

}
