package com.dunkware.trade.tick.service.server.remote;

import java.util.Collection;
import java.util.List;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.api.provider.TickProvider;
import com.dunkware.trade.tick.api.provider.TickProviderException;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class RemoteTickProvider implements TickProvider {
	
	

	@Override
	public void connect(TickProviderSpec providerSpec, TickFeed feed, DExecutor executor) throws TickProviderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSubscribed(TradeTickerSpec ticker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubscribed(String symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TickFeedSubscription getSubscription(TradeTickerSpec ticker) throws TickProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TickFeedSubscription getSubscription(String symbol) throws TickProviderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TickProviderState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TickProviderStatsSpec getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConnectionError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TradeTickerSpec> getInvalidTickers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TickFeedSubscription> getSubscriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getQuoteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTradeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSnapshotCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newDay() {
		// TODO Auto-generated method stub
		
	}
	
	

}
