package com.dunkware.trade.tick.api.feed;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DConverter;
import com.dunkware.common.util.time.DunkTime;
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
	
	private LocalTime lastTradeTime = null;
	private LocalTime lastSnapshotTime = null;
	private LocalTime lastQuoteTime = null;
	
	private int tps = -1;
	private int qps = -1;
	
	
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
		this.lastSnapshotTime = LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		this.snapshotCount.incrementAndGet();
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
		bean.setLastSnapshotUpdate(DunkTime.toStringTimeStamp(snapshot.getTime().get()));
		bean.setId(tickerSpec.getId());
	
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
		this.lastQuoteTime = LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		this.lastQuote = lastQuote;
		this.lastQuote.setTime(DDateTime.now(DTimeZone.NewYork));
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
		this.lastTradeTime = LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		this.lastTrade = lastTrade;
		this.lastPrice = lastTrade.getPrice();
		this.lastTrade.setTime(DDateTime.now(DTimeZone.NewYork));
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
		this.lastSnapshotTime = LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		this.lastSnapshot = lastSnapshot;
		this.askPrice = lastSnapshot.getAskPrice();
		this.askSize = lastSnapshot.getAskSize();
		this.lastPrice = lastSnapshot.getLast();
		this.volume.set(DConverter.longToInt(lastSnapshot.getVolume()));
		this.trades.set(lastSnapshot.getTradeCount());
		
		
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
		bean.setSymbol(symbol);
		if(lastSnapshotTime != null) { 
			bean.setLastSnapshotUpdate(DunkTime.toStringTimeStamp(lastSnapshotTime));
		}
		if(lastTrade != null) { 
			bean.setLastTradeUpdate(DunkTime.toStringTimeStamp(lastTrade.getTime().get()));	
			bean.setLastTradeUpdate(DunkTime.toStringTimeStamp(lastTradeTime));
			
		}
		if(lastQuote != null) { 
			bean.setLastQuoteUpdate(DunkTime.toStringTimeStamp(lastQuote.getTime().get()));
			bean.setAskPrice(lastQuote.getAskPrice());
			bean.setBidSize(lastQuote.getBidSize());
			bean.setAskSize(lastQuote.getAskSize());
			bean.setBidPrice(lastQuote.getBidPrice());
			bean.setLastQuoteUpdate(DunkTime.toStringTimeStamp(lastQuoteTime));
		}
		bean.setTrades(getTrades());
		bean.setVolume(getVolume());
		bean.setLastPrice(getLastPrice());
		
		return bean;
	}
	
	public void addListener(TickFeedListener listener) { 
		
	}
	
	public void removeListener(TickFeedListener listener) { 
		
	}
	
	public void newDay() { 
		this.tradeCount.set(0);
		this.quoteCount.set(0);
		this.snapshotCount.set(0);
	}
	

	public void secondUpdate() { 
		if(qps == -1) { 
			qps = quoteCount.get();
		} else { 
			qps = quoteCount.get() - qps;
		}
		if(tps == -1) { 
			tps = tradeCount.get();
		} else { 
			tps = tradeCount.get() - tps;
		}
	}

}
