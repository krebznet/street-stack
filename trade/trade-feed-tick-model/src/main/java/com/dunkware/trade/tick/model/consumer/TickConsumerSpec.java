package com.dunkware.trade.tick.model.consumer;

import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickConsumerSpec {
	
	private String name; 
	private List<TradeTickerSpec> tickers;
	private Integer[] tickTypes;
	
	public TickConsumerSpec() { 
		
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
