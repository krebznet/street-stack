package com.dunkware.trade.service.streamstats.client.impl;

public interface EntityStatCallBack {

	public void exception(String exception); 
	
	public void resolved(Number value); 
	
	public void timeout();
	
	public void unresolved(String exception);
}
