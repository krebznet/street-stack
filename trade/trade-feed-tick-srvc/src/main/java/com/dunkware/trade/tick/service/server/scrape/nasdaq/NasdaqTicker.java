package com.dunkware.trade.tick.service.server.scrape.nasdaq;

public class NasdaqTicker {

	private String symbol;
	private String name; 
	private double last; 
	private long marketcap; 
	private long volume;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public long getMarketcap() {
		return marketcap;
	}
	public void setMarketcap(long marketcap) {
		this.marketcap = marketcap;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	} 
	
	
}
