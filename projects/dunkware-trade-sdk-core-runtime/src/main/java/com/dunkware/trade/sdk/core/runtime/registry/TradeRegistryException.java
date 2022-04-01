package com.dunkware.trade.sdk.core.runtime.registry;

public class TradeRegistryException extends Exception {


	private static final long serialVersionUID = 7013693152071060255L;

	public TradeRegistryException(String s) {
		super(s);
	}
	
	public TradeRegistryException(String s, Throwable t) { 
		super(s,t);
	}
}
