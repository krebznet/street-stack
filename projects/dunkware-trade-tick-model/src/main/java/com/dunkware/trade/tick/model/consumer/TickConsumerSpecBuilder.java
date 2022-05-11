package com.dunkware.trade.tick.model.consumer;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerType;

public class TickConsumerSpecBuilder {
	
	public static TickConsumerSpecBuilder newInstance(String name) { 
		return new TickConsumerSpecBuilder(name);
	}
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private List<Integer> tickTypes = new ArrayList<Integer>();
	private String name; 
	
	public TickConsumerSpecBuilder(String name) { 
		this.name = name; 
	}
	public TickConsumerSpecBuilder addTicker(TradeTickerSpec ticker) { 
		tickers.add(ticker);
		return this;
	}
	
	public TickConsumerSpecBuilder addEquity(String symbol) {
		TradeTickerSpec ticker = new TradeTickerSpec();
		ticker.setSymbol(symbol);
		ticker.setType(TradeTickerType.Equity);
		tickers.add(ticker);
		return this;
	}
	
	
	public TickConsumerSpecBuilder addTickType(int type) { 
		this.tickTypes.add(type);
		return this;
	}
	
	public TickConsumerSpec build() { 
		TickConsumerSpec sub = new TickConsumerSpec();
		sub.setTickers(tickers);
		sub.setName(name);
		sub.setTickTypes(tickTypes.toArray(new Integer[tickTypes.size()]));
		return sub;
	}


}
