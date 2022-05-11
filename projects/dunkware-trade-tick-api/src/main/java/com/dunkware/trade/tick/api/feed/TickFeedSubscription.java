package com.dunkware.trade.tick.api.feed;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedSnapshot;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedSubscription {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AtomicInteger quoteCount = new AtomicInteger();
	private AtomicInteger tradeCount = new AtomicInteger();
	private AtomicInteger snapshotCount = new AtomicInteger();
	
	
	private TickFeedQuote lastQuote; 
	private TickFeedTrade lastTrade; 
	private TickFeedSnapshot lastSnapshot;
	
	private String symbol; 
	private double lastPrice; 
	private double askPrice; 
	private double bidPrice; 
	private AtomicLong volume = new AtomicLong(0);
	private AtomicInteger trades = new AtomicInteger(0);
	private int bidSize; 
	private int askSize; 
	
	
	TickFeedSubscriptionBean bean = new TickFeedSubscriptionBean();
	
	private TradeTickerSpec tickerSpec; 
	
	public TickFeedSubscription(TickFeedSnapshot snapshot, TradeTickerSpec tickerSpec) { 
		this.lastSnapshot = snapshot; 
		this.tickerSpec = tickerSpec;
		this.volume.set(snapshot.getVolume());
		this.lastPrice = snapshot.getLast();
		this.trades.set(snapshot.getTradeCount());
		this.bidPrice = snapshot.getBidPrice();
		this.askPrice = snapshot.getAskPrice();
		this.symbol = snapshot.getSymbol();
		bean.setAskPrice(snapshot.getAskPrice());
		bean.setAskSize(snapshot.getAskSize());
		bean.setLastPrice(snapshot.getLast());
		bean.setVolume(snapshot.getVolume());
		bean.setTrades(snapshot.getTradeCount());
		bean.setLastSnapshotUpdate(snapshot.getTime());
		if(logger.isTraceEnabled()) { 
			logger.trace("SUB-NEW SYM={} VOL{} LAST{} TC{}",this.symbol,this.volume,this.lastPrice,this.tradeCount);
		}
	}
	
	public int getIdentifier() { 
		return tickerSpec.getId();
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public TickFeedQuote getLastQuote() {
		return lastQuote;
	}
	public void setLastQuote(TickFeedQuote lastQuote) {
		this.lastQuote = lastQuote;
		quoteCount.incrementAndGet();
		askPrice = lastQuote.getAskPrice();
		bidPrice = lastQuote.getBidPrice();
		bidSize = lastQuote.getBidSize();
		askSize = lastQuote.getAskSize();
		if(logger.isTraceEnabled()) { 
			logger.trace("SUB-QUOTE SYM={} AP{} BP{} AS{} BSP{}",this.symbol,this.askPrice,this.bidPrice,this.askSize,this.bidSize);
		}
		
	}
	public TickFeedTrade getLastTrade() {
		return lastTrade;
	}
	public void setLastTrade(TickFeedTrade lastTrade) {
		this.lastTrade = lastTrade;
		this.lastPrice = lastTrade.getPrice();
		this.volume.addAndGet(lastTrade.getSize());
		tradeCount.incrementAndGet();
		trades.incrementAndGet();
		if(logger.isTraceEnabled()) { 
			logger.trace("SUB-TRADE SYM={} VOL{} BP{} TC{}",this.symbol,this.volume,this.trades);
		}
	}
	public TickFeedSnapshot getLastSnapshot() {
		return lastSnapshot;
	}
	public void setLastSnapshot(TickFeedSnapshot lastSnapshot) {
		this.lastSnapshot = lastSnapshot;
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
	public double getBidPrice() {
		return bidPrice;
	}
	
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	public long getVolume() {
		return volume.get();
	}
	
	public void setVolume(long volume) {
		this.volume.set(volume);
	}
	
	public int getTrades() {
		return trades.get();
	}
	public void setTrades(int trades) {
		this.trades.set(trades);
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
	
	public TickFeedSubscriptionBean getBean() { 
		bean.setQuoteCount(quoteCount.get());
		bean.setTradeCount(tradeCount.get());
		bean.setSnapshotCount(snapshotCount.get());
		bean.setLastTradeUpdate(lastTrade.getTime());
		return bean;
	}
	
	public void addListener(TickFeedListener listener) { 
		
	}
	
	public void removeListener(TickFeedListener listener) { 
		
	}
	
	public void newDay() { 
		
	}
	

}
