package com.dunkware.trade.sdk.core.runtime.trade;

/**
 * Happens when things go wrong that should not go wrong. what about a broker disconnect?
 * @author duncankrebs
 *
 */
public class TradeException extends Exception {

	public TradeException(String s) {
		super(s);
	}
	
	public TradeException(String s, Throwable t) { 
		super(s,t);
	}
}
