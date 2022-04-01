package com.dunkware.trade.tick.model.feed;

import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedSpec {
	
	private String name; 
	private List<TradeTickerSpec> tickers;
	private Integer[] tickTypes;
	
	public TickFeedSpec() { 
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}
	public void setTickers(List<TradeTickerSpec> tickers) {
		this.tickers = tickers;
	}

	public Integer[] getTickTypes() {
		return tickTypes;
	}

	public void setTickTypes(Integer[] tickTypes) {
		this.tickTypes = tickTypes;
	}
	
	
	
	
}
