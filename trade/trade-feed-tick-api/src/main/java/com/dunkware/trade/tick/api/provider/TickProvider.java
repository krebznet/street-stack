package com.dunkware.trade.tick.api.provider;

import java.util.Collection;
import java.util.List;

import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.utils.core.concurrent.DunkExecutor;

public interface TickProvider  {

	public void connect(TickProviderSpec providerSpec, TickFeed feed, DunkExecutor executor) throws TickProviderException;
	
	
	public void subscribeTickers(Collection<TradeTickerSpec> tickers) throws Exception;

	/**
	 * Returns true if a ticker is already subscribed, false if not
	 * @param ticker
	 * @return
	 */
	public boolean isSubscribed(TradeTickerSpec ticker);
	
	public boolean isSubscribed(String symbol);
	/**
	 * 
	 * Subscribes a ticker from the list of active subscriptions
	 * @param ticker
	 * @throws TickReactorException
	 */
	public TickFeedSubscription getSubscription(TradeTickerSpec ticker) throws TickProviderException;
	
	
	public TickFeedSubscription getSubscription(String symbol) throws TickProviderException;
	
	/**
	 * Returns the status so we know if its connected,disconnected etc. 
	 * @return
	 */
	public TickProviderState getState();
	
	/**
	 * Returns the stats for the provier. 
	 * @return
	 */
	public TickProviderStatsSpec getStats();
	
	/**
	 * Returns the connection error if status is equal to Error
	 * @return
	 */
	public String getConnectionError();
	
	/**
	 * Returns invalid subscriptions 
	 * @return
	 */
	public List<TradeTickerSpec> getInvalidTickers();
	
	/**
	 * returns the subscriptions. 
	 * @return
	 */
	public Collection<TickFeedSubscription> getSubscriptions();
	
	/**
	 * returns the quote count for today 
	 * @return
	 */
	public int getQuoteCount();
	
	/**
	 * returns the snapshto count for today
	 * @return
	 */
	public int getTradeCount();
	
	/**
	 * returns snapshot count
	 * @return
	 */
	public int getSnapshotCount();
	
	/**
	 * Called by controller on day change
	 */
	public void newDay(); 
	
	
	
}
