package com.dunkware.trade.engine.api.session.model;

public class XTradePolicy {

	private boolean tradeLimitEnabled; 
	private int tradeLimit; 
	private boolean activeTickerLimitEnabled; 
	private int activeTickerLimit; 
	private boolean activeTradeLimitEnabled; 
	private int activeTradeLimit; 
	private boolean tickerThrottleEnabled;
	private int tickerThrottleSeconds; 
	private boolean capitalLimitEnabled; 
	private double capitalLimit; 
}
