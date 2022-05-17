package com.dunkware.trade.tick.model.feed;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedSubscriptionBean {
	
	private int tradeCount; 
	private int quoteCount; 
	private int snapshotCount;
	
	private String symbol; 
	private double lastPrice; 
	private double askPrice; 
	private int bidSize; 
	private int askSize; 
	private long volume; 
	private long trades;
	private double bidPrice; 
	
	private String lastTradeUpdate; 
	private String lastQuoteUpdate; 
	private String lastSnapshotUpdate;
	
	private int id; 
	
	
	private int tps; 
	private int qps; 
	
	
	private TradeTickerSpec tickerSpec;
	
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(int quoteCount) {
		this.quoteCount = quoteCount;
	}
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
	
	public TradeTickerSpec getTickerSpec() {
		return tickerSpec;
	}
	public void setTickerSpec(TradeTickerSpec tickerSpec) {
		this.tickerSpec = tickerSpec;
	}
	public String getLastTradeUpdate() {
		return lastTradeUpdate;
	}
	public void setLastTradeUpdate(String lastTradeUpdate) {
		this.lastTradeUpdate = lastTradeUpdate;
	}
	public String getLastQuoteUpdate() {
		return lastQuoteUpdate;
	}
	public void setLastQuoteUpdate(String lastQuoteUpdate) {
		this.lastQuoteUpdate = lastQuoteUpdate;
	}
	public String getLastSnapshotUpdate() {
		return lastSnapshotUpdate;
	}
	public void setLastSnapshotUpdate(String lastSnapshotUpdate) {
		this.lastSnapshotUpdate = lastSnapshotUpdate;
	}
	public int getTps() {
		return tps;
	}
	public void setTps(int tps) {
		this.tps = tps;
	}
	public int getQps() {
		return qps;
	}
	public void setQps(int qps) {
		this.qps = qps;
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
	
	
	
	
	
	
	
	
	
	
}
