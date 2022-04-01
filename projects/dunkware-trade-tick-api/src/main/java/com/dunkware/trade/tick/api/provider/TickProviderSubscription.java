package com.dunkware.trade.tick.api.provider;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public interface TickProviderSubscription extends TickStream {

	/**
	 * Returns the ticker
	 * @return
	 */
	TradeTickerSpec getTicker();
	
	
	/**
	 * Returns the status so we know if its a valid symbol or invalid or pending. 
	 * @return
	 */
	TickProviderSubscriptionStatus getStatus();
	
	/**
	 * Returns the symbol that the provider generated via tickerToString() method
	 * @return
	 */
	String getSymbol();
	
	/**
	 * Returns the number of active permits via acquire and release subscription
	 * on tick provider
	 * @return
	 */
	int getPermits();
}
