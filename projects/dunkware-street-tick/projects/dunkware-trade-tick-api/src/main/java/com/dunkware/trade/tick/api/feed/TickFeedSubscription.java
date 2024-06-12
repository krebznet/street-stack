package com.dunkware.trade.tick.api.feed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedSnapshot;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.utils.core.helpers.DunkNumber;
import com.dunkware.utils.core.time.DunkTimeZones;

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
	private double openPrice; 
	private double closePrice; 
	private long premarketVolume; 
	private int premarketTrades; 
	private double extendedHoursLastPrice;
	
	
	TickFeedSubscriptionBean bean = new TickFeedSubscriptionBean();
	
	private TradeTickerSpec tickerSpec; 
	
	public TickFeedSubscription(TickFeedSnapshot snapshot, TradeTickerSpec tickerSpec) { 
		this.lastSnapshotTime = LocalTime.now(DunkTimeZones.zoneNewYork());
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
		this.lastQuoteTime = LocalTime.now(DunkTimeZones.zoneNewYork());
		this.lastQuote = lastQuote;
		this.lastQuote.setTime(LocalDateTime.now(DunkTimeZones.zoneNewYork()));
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
		this.lastTradeTime = LocalTime.now(DunkTimeZones.zoneNewYork());
		this.lastTrade = lastTrade;
		this.lastPrice = lastTrade.getPrice();
		this.lastTrade.setTime(LocalDateTime.now(DunkTimeZones.zoneNewYork()));
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
		this.lastSnapshotTime = LocalTime.now(DunkTimeZones.zoneNewYork());
		this.lastSnapshot = lastSnapshot;
		this.askPrice = lastSnapshot.getAskPrice();
		this.askSize = lastSnapshot.getAskSize();
		this.lastPrice = lastSnapshot.getLast();
		this.volume.set(DunkNumber.longToInt(lastSnapshot.getVolume()));
		this.trades.set(lastSnapshot.getTradeCount());
		this.bidPrice = lastSnapshot.getBidPrice();
		this.bidSize = lastSnapshot.getBidSize();
		this.openPrice = lastSnapshot.getOpenPrice();
		this.closePrice = lastSnapshot.getClosePrice();
		this.extendedHoursLastPrice = lastSnapshot.getExtendedHoursLastPrice();
		this.premarketTrades = lastSnapshot.getPremarketTradeCount();
		this.premarketVolume = lastSnapshot.getPremarketVolume();
		
		
	}
	
	

	public long getPremarketVolume() {
		return premarketVolume;
	}

	public void setPremarketVolume(long premarketVolume) {
		this.premarketVolume = premarketVolume;
	}

	public int getPremarketTrades() {
		return premarketTrades;
	}

	public void setPremarketTrades(int premarketTrades) {
		this.premarketTrades = premarketTrades;
	}

	public double getExtendedHoursLastPrice() {
		return extendedHoursLastPrice;
	}

	public void setExtendedHoursLastPrice(double extendedHoursLastPrice) {
		this.extendedHoursLastPrice = extendedHoursLastPrice;
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

	public TickFeedSubscriptionBean getBean() { 
	
		bean.setSnapshotCount(snapshotCount.get());
		bean.setSymbol(symbol);
		
		
		
		if(lastQuote != null) { 
			
			bean.setAskPrice(lastQuote.getAskPrice());
			bean.setBidSize(lastQuote.getBidSize());
			bean.setAskSize(lastQuote.getAskSize());
			bean.setBidPrice(lastQuote.getBidPrice());
			
		}
		bean.setTrades(getTrades());
		bean.setVolume(getVolume());
		bean.setLastPrice(getLastPrice());
		bean.setAskPrice(askPrice);
		bean.setExtendedLast(extendedHoursLastPrice);
	
		bean.setOpenPrice(openPrice);
		bean.setClosePrice(closePrice);
		
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
