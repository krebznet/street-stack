package com.dunkware.trade.tick.service.protocol.ticker.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TradeTickerListSpec {

	private String name; 
	private int size; 
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private int id;
	
	public TradeTickerListSpec() { 
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}
	public void setTickers(List<TradeTickerSpec> tickers) {
		this.tickers = tickers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	
	
}
