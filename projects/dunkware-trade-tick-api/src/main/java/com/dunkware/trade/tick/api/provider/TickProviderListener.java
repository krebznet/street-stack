package com.dunkware.trade.tick.api.provider;

public interface TickProviderListener {

	public void invalidSubscription(TickProvider provider, TickProviderSubscription subscription);
	
	public void statusUpdate(TickProvider provider);
	
	public void internalException(TickProvider provider, String exception);
}
