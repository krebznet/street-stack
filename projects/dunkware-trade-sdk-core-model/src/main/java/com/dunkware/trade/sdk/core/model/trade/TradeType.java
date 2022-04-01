package com.dunkware.trade.sdk.core.model.trade;

import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TradeType {
	
	private double capital; 
	private TradeTickerSpec ticker;
	private EntryType entry; 
	private ExitType exit;
	private TradeSide side; 
	private String name; 
	
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public TradeTickerSpec getTicker() {
		return ticker;
	}
	public void setTicker(TradeTickerSpec ticker) {
		this.ticker = ticker;
	}
	public EntryType getEntry() {
		return entry;
	}
	public void setEntry(EntryType entry) {
		this.entry = entry;
	}
	public ExitType getExit() {
		return exit;
	}
	public void setExit(ExitType exit) {
		this.exit = exit;
	}
	public TradeSide getSide() {
		return side;
	}
	public void setSide(TradeSide side) {
		this.side = side;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	
	
	
	

}
