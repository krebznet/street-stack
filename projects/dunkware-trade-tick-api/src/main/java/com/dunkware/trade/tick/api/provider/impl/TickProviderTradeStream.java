package com.dunkware.trade.tick.api.provider.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.TickHelper;
import com.dunkware.common.tick.filter.TickFilter;
import com.dunkware.common.tick.filter.TickFilterBuilder;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.stream.impl.TickStreamImpl;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedException;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.api.provider.TickProviderSubscription;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedStatsSpec;
import com.dunkware.trade.tick.model.feed.TickFeedStatus;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickProviderTradeStream extends TickStreamImpl implements TickFeed, TickHandler {
	
	private TickProviderImpl provider;
	private TickFeedSpec spec;
	
	private ConcurrentHashMap<String, TickProviderSubscription> subscribedSymbols = new ConcurrentHashMap<String, TickProviderSubscription>();
	private boolean handlerAttached = false; 
	
	public TickProviderTradeStream(TickProviderImpl provider, TickFeedSpec spec) {
		this.provider = provider;
		this.spec = spec;
	}
	
	public void init() throws TickProviderException { 
		Integer[] tickTypes = spec.getTickTypes();
		TickFilter filter = TickFilterBuilder.typeIncludeFilter(tickTypes);
		subscribedSymbols.clear();
		for (TradeTickerSpec ticker : spec.getTickers()) {
			TickProviderSubscription sub = provider.acquireSubscription(ticker);
			subscribedSymbols.put(provider.tickerToString(ticker), sub);
		}
		provider.addTickHandler(this,filter);
		handlerAttached = true;
	}

	@Override
	public void dispose() {
		if(handlerAttached) { 
			provider.removeTickHandler(this);
		}
		// release subscriptions
		for (TradeTickerSpec ticker : spec.getTickers()) {
			provider.releaseSubscription(ticker);
		}
	}

	
	@Override
	public TickFeedStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TickFeedStatsSpec getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(TickFeedSpec spec) throws TickFeedException {
		if(handlerAttached) { 
			provider.removeTickHandler(this);
			handlerAttached = false;
		}
		try {
			init();	
		} catch (Exception e) {
			throw new TickFeedException("Update Feed Exception " + e.toString(),e);
		}
	}

	

	@Override
	public void onTick(Tick tick) {
		if(subscribedSymbols.containsKey(TickHelper.getField(tick, TradeTicks.FieldSymbol).getStringValue())) {
			streamTick(tick);
		}
	}
	
	

	
	
}
