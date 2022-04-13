package com.dunkware.trade.tick.model.ticker;

public class TradeTickerSpec {

	private int id; 
	private String symbol;
	private String name; 
	private long avgVolume;
	private TradeTickerType type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public long getAvgVolume() {
		return avgVolume;
	}
	public void setAvgVolume(long avgVolume) {
		this.avgVolume = avgVolume;
	}
	public TradeTickerType getType() {
		return type;
	}
	public void setType(TradeTickerType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		return symbol.hashCode();
	}
	@Override
	public String toString() {
		return type.toString() + ":" + symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	
	
	
	
	
	
	
}
