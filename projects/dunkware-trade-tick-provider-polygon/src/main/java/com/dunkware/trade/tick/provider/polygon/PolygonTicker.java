package com.dunkware.trade.tick.provider.polygon;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.trade.tick.provider.polygon.core.PolygonSnapshot;
import com.dunkware.trade.tick.provider.polygon.core.event.PolygonAggEvent;

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
	
	private LocalDateTime lastUpdate = null;
	
	private volatile int aggCount = 0;
	private volatile int snapshotCount = 0; 
	
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
	
	public void setTradeCount(AtomicInteger tradeCount) {
		this.tradeCount = tradeCount;
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
	
	public double getLastPrice() {
		return lastPrice;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public int getAggCount() {
		return aggCount;
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
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void agg(PolygonAggEvent event) { 
		this.volume = event.getVolume();
		int tickTradeCount = event.getTickVolume() / event.getTickAverageTradeSize();
		tradeCount.addAndGet(tickTradeCount);
		this.tickVwap = event.getTickVwap();
		// parse the lastUpdate; 
		this.lastPrice = event.getTickClose();
		this.aggCount++;
	}
	
	public void snapshot(PolygonSnapshot snapshot) { 
		this.snapshotCount++;
		this.askPrice = snapshot.getLastQuote().getAskPrice();
		this.bidPrice = snapshot.getLastQuote().getBidPrice();
		this.askSize = snapshot.getLastQuote().getAskSize();
		this.bidSize = snapshot.getLastQuote().getBidSize();
		this.change = snapshot.getTodaysChange();
		this.change = snapshot.getTodaysChangePercent();
		
	}
	
	

	
}
