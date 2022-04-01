package com.dunkware.trade.tick.model.instrument;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class EquityQuote {
	
	private String symbol;
	private double last; 
	private double askPrice; 
	private double bidPrice; 
	private int askSize; 
	private int bidSize; 
	
	private DDateTime time; 
	
	
	public EquityQuote() { 
		
	}
	// Equity EquityListener equityQuote
	// EquityQuoteListener
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	
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
