package com.dunkware.trade.tick.service.protocol.feed;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedSubscriptions {
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();

	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}

	public void setTickers(List<TradeTickerSpec> tickers) {
		this.tickers = tickers;
	}
	
	

}
