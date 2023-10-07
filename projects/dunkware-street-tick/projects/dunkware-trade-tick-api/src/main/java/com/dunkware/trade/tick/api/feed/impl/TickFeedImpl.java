package com.dunkware.trade.tick.api.feed.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedException;
import com.dunkware.trade.tick.api.feed.TickFeedListener;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedStats;
import com.dunkware.trade.tick.model.feed.TickFeedSubscriptionBean;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedImpl implements TickFeed {

	
	private DExecutor executor; 
	private TickProvider provider; 
	
	private List<TickFeedListener> listeners = new ArrayList<TickFeedListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private String kafkaBrokers;
	
	
	private Map<String,TradeTickerSpec> tradeTickers = new ConcurrentHashMap<String,TradeTickerSpec>();
	
	private AtomicInteger quoteCounter = new AtomicInteger(0);
	private AtomicInteger tradeCounter = new AtomicInteger(0);
	private AtomicInteger messageCounter = new AtomicInteger(0);
	
	private int mps = -1;
	private int qps = -1;
	private int tps = -1;
	
	private TickFeedStats stats = new TickFeedStats();
	
	private SecondUpdater secUpdater = new SecondUpdater();
	@Override
	public void start(TickProvider provider, DExecutor exector, String kafkaBrokers) throws TickFeedException {
		this.executor = exector;
		this.provider = provider; 
		this.kafkaBrokers = kafkaBrokers;
		secUpdater.start();
	}

	@Override
	public void subscribe(Collection<TradeTickerSpec> specs) throws TickFeedException {
		try {
			for (TradeTickerSpec tradeTickerSpec : specs) {
				tradeTickers.put(tradeTickerSpec.getSymbol(), tradeTickerSpec);
			}
			provider.subscribeTickers(specs);			
		} catch (Exception e) {
			throw new TickFeedException("Exception subscribing to tickers provider exception " + e.toString());
		}

	}

	@Override
	public void addListener(TickFeedListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public void removeListener(TickFeedListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public Collection<TickFeedSubscription> getSubscriptions() {
		return provider.getSubscriptions();
	}

	@Override
	public TickFeedSubscription getSubscription(String ticker) throws TickFeedException {
		try {
			return provider.getSubscription(ticker);			
		} catch (Exception e) {
			throw new TickFeedException("Exception getting subscription " + e.toString());
		}

	}
	
	@Override
	public TickFeedStats getStats() {
		stats.setSubscriptionCount(provider.getSubscriptions().size());
		stats.setQuoteCount(quoteCounter.get());
		stats.setTradeCount(tradeCounter.get());
		stats.setMessageCount(messageCounter.get());
		stats.setQps(qps);
		stats.setTps(tps);
		stats.setMps(mps);
		TickProviderStatsSpec pStats = this.provider.getStats();
		stats.setLastStreamMessage(pStats.getLastStreamMessage());
		stats.setLastSnapshotMessage(pStats.getLastSnapshotMessage());
		return stats;
	}
	
	

	@Override
	public void onTrade(TickFeedTrade trade) {
		tradeCounter.incrementAndGet();
		messageCounter.incrementAndGet();
	}

	@Override
	public void onQuote(TickFeedQuote quote) {
		quoteCounter.incrementAndGet();
		messageCounter.incrementAndGet();
		
	}

	@Override
	public TradeTickerSpec getTickerSpec(String symbol) {
		return tradeTickers.get(symbol);
	}

	@Override
	public int getSymbolId(String symbol) {
		return tradeTickers.get(symbol).getId();
	}

	@Override
	public boolean hasSubscription(String ticker) {
		if(this.provider.isSubscribed(ticker)) { 
			return true;
		}
		return false; 
	}

	@Override
	public DExecutor getExecutor() {
		return executor;
	}

	@Override
	public TickFeedSubscriptionBean getSubscriptionBean(String symbol) throws TickFeedException {
		TickFeedSubscription sub = getSubscription(symbol);
		if(sub == null) { 
			throw new TickFeedException("Subscription not found for " + symbol);
		}
		return sub.getBean();
	}

	@Override
	public List<TickFeedSubscriptionBean> getSubscriptionBeans() {
		List<TickFeedSubscriptionBean> beans = new ArrayList<TickFeedSubscriptionBean>();
		for (TickFeedSubscription sub : getSubscriptions()) {
			beans.add(sub.getBean());
		}
		return beans;
	}

	@Override
	public int getSubscriptionCount() {
		return getSubscriptions().size();
	}

	@Override
	public int getTradeTickCount() {
		return this.tradeTickers.size();
	}

	@Override
	public String getConsumerBrokers() {
		return kafkaBrokers;
	}
	
	
	private class SecondUpdater extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					for (TickFeedSubscription subscription : getSubscriptions()) {
						subscription.secondUpdate();
					}
					if(tps == -1) { 
						tps = tradeCounter.get();
					} else { 
						tps = tradeCounter.get() - tps; 
					}
					if(qps == -1) { 
						qps =  quoteCounter.get();
					} else { 
						qps =  quoteCounter.get() - qps;
					}
					if(mps == -1) { 
						mps = messageCounter.get();
					} else { 
						mps = messageCounter.get() - mps;
					}
					Thread.sleep(1000);
				
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	

	

}
