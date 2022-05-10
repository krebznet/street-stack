package com.dunkware.trade.tick.api.provider.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.TickHelper;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.api.provider.TickProviderListener;
import com.dunkware.trade.tick.api.provider.TickProviderSubscription;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public abstract class TickProviderImpl extends TickStreamImpl implements TickProvider {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected ConcurrentHashMap<TradeTickerSpec, TickProviderSubscription> subscriptions = new ConcurrentHashMap<TradeTickerSpec, TickProviderSubscription>();
	protected String connectionError = null;
	private TickProviderState status = TickProviderState.CREATED;
	private List<TickProviderListener> providerListeners = new ArrayList<TickProviderListener>();
	private Semaphore providerListenerLock = new Semaphore(1);


	
	@Override
	public TickFeed createFeed(TickFeedSpec spec) throws TickProviderException {
		TickProviderTradeStream stream = new TickProviderTradeStream(this, spec);
		stream.init();
		return stream;
	}

	@Override
	public Collection<TickProviderSubscription> getSubscriptions() {
		return subscriptions.values();
	}

	@Override
	public TickProviderSubscription getSubscription(TradeTickerSpec ticker) {
		return subscriptions.get(ticker);
	}

	@Override
	public boolean isSubscribed(TradeTickerSpec ticker) {
		if(subscriptions.containsKey(ticker)) { 
			return true;
		}
		return false;
	}
	
	@Override
	public TickProviderSubscription acquireSubscription(TradeTickerSpec ticker) throws TickProviderException {
		if(subscriptions.containsKey(ticker)) { 
			TickProviderSubscriptionImpl subscription = (TickProviderSubscriptionImpl)subscriptions.get(ticker);	
			subscription.incrementPermits();
			return subscription;
		}
		
		TickProviderSubscriptionImpl subscription = new TickProviderSubscriptionImpl(ticker,tickerToString(ticker));
		subscription.incrementPermits();
		subscriptions.put(ticker, subscription);
		subscribe(subscription.getSymbol());
		return subscription;
	}

	@Override
	public void releaseSubscription(TradeTickerSpec ticker) {
		if(subscriptions.containsKey(ticker) == false) { 
			logger.warn("Release Subscription " + ticker.toString() + " not found");
			return;
		}
		TickProviderSubscriptionImpl sub = (TickProviderSubscriptionImpl)subscriptions.get(ticker);
		sub.decrementPermits();
		if(sub.getPermits() == 0) { 
			unsubscribe(sub.getSymbol());
			subscriptions.remove(ticker);
		}
	}
	

	@Override
	public void addProviderListener(TickProviderListener listener) {
		try {
			providerListenerLock.acquire();
			providerListeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			providerListenerLock.release();
		}
	}

	@Override
	public void removeProviderListener(TickProviderListener listener) {
		try {
			providerListenerLock.acquire();
			providerListeners.remove(listener);
		} catch (Exception e) {
	
		} finally { 
			providerListenerLock.release();
		}
	}
	
	

	@Override
	public String getConnectionError() {
		return connectionError;
	}

	protected void setConnectionError(String error) { 
		this.connectionError = error;
	}
	
	protected void setStatus(TickProviderState status) { 
		if(this.status != status) { 
			this.status = status;
			notifyStatusUpdate();
		}
	}
	
	protected abstract void subscribe(String symbol) throws TickProviderException;

	protected abstract void unsubscribe(String symbol);
	
	private void notifyStatusUpdate() { 
		try {
			providerListenerLock.acquire();
			for (TickProviderListener tickProviderListener : providerListeners) {
				tickProviderListener.statusUpdate(this);
			}
		} catch (Exception e) {
			
		} finally { 
			providerListenerLock.release();
		}
	}
	
	
	@Override
	public void streamTick(Tick tick) {
		super.streamTick(tick);
		if(logger.isTraceEnabled()) { 
			logger.trace("Tick:" + TickHelper.printTick(tick));
		}
	}

	protected void notifyInternalException(String exception) { 
		try {
			providerListenerLock.acquire();
			for (TickProviderListener tickProviderListener : providerListeners) {
				tickProviderListener.internalException(this, exception);
			}
		} catch (Exception e) {
			
		} finally { 
			providerListenerLock.release();
		}
	}
	
	protected void notifyInvalidSubscription(TickProviderSubscription subscription) { 
		try {
			providerListenerLock.acquire();
			for (TickProviderListener tickProviderListener : providerListeners) {
				tickProviderListener.invalidSubscription(this, subscription);
			}
		} catch (Exception e) {
			
		} finally { 
			providerListenerLock.release();
		}
	}
}
