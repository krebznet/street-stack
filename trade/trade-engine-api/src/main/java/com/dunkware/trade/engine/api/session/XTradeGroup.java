package com.dunkware.trade.engine.api.session;

import com.dunkware.trade.api.data.ticker.Ticker;

public interface XTradeGroup {
	
	public double getActiveCapital();
	
	public int getActiveTickerTradeCount(Ticker ticker);
	
	public int getTickerTradeCount(Ticker ticker);
	
	

}
