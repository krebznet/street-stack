package com.dunkware.trade.tick.api.feed.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedException;
import com.dunkware.trade.tick.api.feed.TickFeedListener;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.model.feed.TickFeedBean;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedImpl implements TickFeed {

	private DExecutor executor; 
	private TickProvider provider; 
	
	private List<TickFeedListener> listeners = new ArrayList<TickFeedListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private String kafkaBrokers;
	private TickFeedBean bean = new TickFeedBean();
	
	private Map<String,TradeTickerSpec> tradeTickers = new ConcurrentHashMap<String,TradeTickerSpec>();
	
	@Override
	public void start(TickProvider provider, DExecutor exector, String kafkaBrokers) throws TickFeedException {
		this.executor = exector;
		this.provider = provider; 
		this.kafkaBrokers = kafkaBrokers;
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
	public TickFeedBean getBean() {
		bean.setQuoteCount(provider.getQuoteCount());
		bean.setTradeCount(provider.getTradeCount());
		bean.setSnapshotCount(provider.getSnapshotCount());
		return bean;
	}

	@Override
	public String getConsumerBrokers() {
		return kafkaBrokers;
	}
	
	

	

}
