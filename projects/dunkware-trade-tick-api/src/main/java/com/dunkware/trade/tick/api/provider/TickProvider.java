package com.dunkware.trade.tick.api.provider;

import java.util.Collection;
import java.util.List;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedTicker;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;
import com.dunkware.trade.tick.model.provider.TickProviderState;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface TickProvider extends TickStream {

	public void connect(TickProviderSpec type, TradeSymbolService idProvider, DExecutor executor) throws TickProviderException;
	
	public TickFeed createFeed(TickFeedSpec spec) throws TickProviderException;
	
	public Collection<TickProviderSubscription> getSubscriptions();
	
	public void subscribeTickers(Collection<TradeTickerSpec> tickers);
	
	/**
	 * Returns a tick provider subscription 
	 * @param ticker
	 * @return
	 */
	public TickProviderSubscription getSubscription(TradeTickerSpec ticker);
	
	/**
	 * Returns true if a ticker is already subscribed, false if not
	 * @param ticker
	 * @return
	 */
	public boolean isSubscribed(TradeTickerSpec ticker);
	
	/**
	 * Subscribes a ticker from the list of active subscriptions
	 * @param ticker
	 * @throws TickReactorException
	 */
	public TickProviderSubscription acquireSubscription(TradeTickerSpec ticker) throws TickProviderException;
	
	/**
	 * Unsubscribes a ticker from the list of active subscriptions
	 * @param ticker
	 */
	public void releaseSubscription(TradeTickerSpec ticker);
	
	/**
	 * Allows different tick providers to construct ticker
	 * strings differently which are used as symbols when
	 * subscribing to tickers. 
	 * @param ticker
	 * @return
	 */
	public String tickerToString(TradeTickerSpec ticker);
	
	/**
	 * Adds a tick provider listener
	 * @param listener
	 */
	public void addProviderListener(TickProviderListener listener);
	
	/**
	 * Removes a tick provider listener
	 * @param listener
	 */
	public void removeProviderListener(TickProviderListener listener);

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
	 * Gets a ticker if does not exist set -1 on price. 
	 * @param symbol
	 * @return
	 */
	public TickFeedTicker getFeedTicker(String symbol);
	
	public List<TradeTickerSpec> getValidatedSubscriptions();
	
	public List<TradeTickerSpec> getInValidatedSubscriptions();
	
	
	
}
