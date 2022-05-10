package com.dunkware.trade.tick.provider.polygon;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.trade.tick.provider.polygon.core.PolygonSnapshot;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonQuote;

public class PolygonTicker {
	
	private String symbol; 
	private AtomicInteger tradeCount = new AtomicInteger(-1);
	private volatile double tickVwap = 1; 
	private volatile int volume = -1;
	private volatile double askPrice = -1; 
	private volatile int askSize = -1;
	private volatile double bidPrice = -1;
	private volatile int bidSize = -1;
	private double lastPrice = -1;
	private double change = -1;
	private double percentChange = -1; 
	
	private LocalDateTime lastQuote = null;
	private LocalDateTime lastAgg = null;
	
	private boolean resetDay = false; 
	
	private AtomicInteger aggCount = new AtomicInteger();
	private AtomicInteger quoteQuote = new AtomicInteger(); 
	
	private volatile int secondAggCount = -1;
	private volatile int secondQuoteCount = -1;
	
	private int qps = 0; 
	private int aps = 0;
	
	private boolean valid = true; 
	
	private boolean quoteSet = false; 
	private boolean aggSet = false; 
	
	public PolygonTicker(String s) { 
		this.symbol = s;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public AtomicInteger getTradeCount() {
		return tradeCount;
	}

	public int getVolume() {
		return volume;
	}
	
	public void resetDay() { 
		resetDay = true; 
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
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
	
	
	public double getTickVwap() {
		return tickVwap;
	}
	public double getChange() {
		return change;
	}
	public double getPercentChange() {
		return percentChange;
	}
	
	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public boolean isValidated() { 
		if(aggSet && quoteSet) { 
			return true;
		}
		return false; 
	}
	public void agg(PolygonAggEvent event) { 
		aggSet = true;
		this.volume = event.getVolume();
		int tickTradeCount = event.getTickVolume() / event.getTickAverageTradeSize();
		if(resetDay) { 
			tradeCount.set(0);
			resetDay = false;
		}
		tradeCount.addAndGet(tickTradeCount);
		this.tickVwap = event.getTickVwap();
		// parse the lastUpdate; 
		this.lastPrice = event.getTickClose();
		this.aggCount.incrementAndGet();
	}
	
	public void quote(PolygonQuote quote) {
		quoteSet = true;
		this.askPrice = quote.getAskPrice();
		this.askSize = quote.getAskSize(); 
		this.bidPrice = quote.getBidPrice();
		this.bidSize = quote.getBidSize();
		this.quoteQuote.incrementAndGet();
	}
	
	public int getQuoteCount() { 
		return quoteQuote.get();
	}
	
	public int getAggCount() { 
		return aggCount.get();
	}
	
	public int getQPS() { 
		return qps;
	}
	
	public int getAPS() {
		return aps;
	}
	
	public void secondUpdate() { 
		if(secondAggCount == -1) { 
			secondAggCount = aggCount.get();
		} else { 
			secondAggCount = aggCount.get() - secondAggCount;
		}
		if(secondQuoteCount == -1) { 
			secondQuoteCount = aggCount.get();
		} else { 
			secondQuoteCount = quoteQuote.get() - secondQuoteCount;
		}
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	

	
}
