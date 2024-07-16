package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.engine.api.context.TradeContext;

public interface TradeStrategy {
	
	
	TradeContext getContext();
	
	String getName();
	
	public List<Trade> getTrades();
	
}
