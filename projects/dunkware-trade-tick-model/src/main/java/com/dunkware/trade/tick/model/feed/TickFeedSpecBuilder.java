package com.dunkware.trade.tick.model.feed;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

public class TickFeedSpecBuilder {
	
	public static TickFeedSpecBuilder newInstance(String name) { 
		return new TickFeedSpecBuilder(name);
	}
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private List<Integer> tickTypes = new ArrayList<Integer>();
	private String name; 
	
	public TickFeedSpecBuilder(String name) { 
		this.name = name; 
	}
	public TickFeedSpecBuilder addTicker(TradeTickerSpec ticker) { 
		tickers.add(ticker);
		return this;
	}
	
	public TickFeedSpecBuilder addEquity(String symbol) {
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setSymbol(symbol);
		ticker.setType(TradeTickerType.Equity);
		tickers.add(ticker);
		return this;
	}
	
	
	public TickFeedSpecBuilder addTickType(int type) { 
		this.tickTypes.add(type);
		return this;
	}
	
	public TickFeedSpec build() { 
		TickFeedSpec sub = new TickFeedSpec();
		sub.setTickers(tickers);
		sub.setName(name);
		sub.setTickTypes(tickTypes.toArray(new Integer[tickTypes.size()]));
		return sub;
	}


}
