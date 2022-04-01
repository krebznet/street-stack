package com.dunkware.trade.tick.api.provider.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.trade.tick.api.provider.TickProviderSubscription;
import com.dunkware.trade.tick.api.provider.TickProviderSubscriptionStatus;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickProviderSubscriptionImpl extends TickStreamImpl implements TickProviderSubscription {
	
	private TradeTickerSpec ticker;
	private TickProviderSubscriptionStatus status = TickProviderSubscriptionStatus.Pending;
	private String symbol;
	private AtomicInteger permits = new AtomicInteger(0);
	
	public TickProviderSubscriptionImpl(TradeTickerSpec ticker, String symbol) {
		this.ticker = ticker;
		this.symbol = symbol;
	}
	
	@Override
	public TradeTickerSpec getTicker() {
		return ticker;
	}
	
	
	@Override
	public TickProviderSubscriptionStatus getStatus() {
		return status;
	}
	
	public void setStatus(TickProviderSubscriptionStatus status) { 
		if(this.status != status) { 
			this.status = status;
			// notify?
		}
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public int getPermits() {
		return permits.get();
	}
	
	public void incrementPermits() { 
		permits.incrementAndGet();
	}
	
	public void decrementPermits() { 
		permits.decrementAndGet();
	}
	
	
	
	
	
	
	
	
	

	
	
}
