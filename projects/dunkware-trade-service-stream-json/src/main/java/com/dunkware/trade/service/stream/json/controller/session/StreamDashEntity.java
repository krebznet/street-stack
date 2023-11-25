package com.dunkware.trade.service.stream.json.controller.session;

public class StreamDashEntity {

	private String symbol; 
	private String name; 
	private Number last; 
	private Number volume; 
	private Number tradeCount;
	private int id; 
	
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
	public Number getLast() {
		return last;
	}
	public void setLast(Number last) {
		this.last = last;
	}
	public Number getVolume() {
		return volume;
	}
	public void setVolume(Number volume) {
		this.volume = volume;
	}
	public Number getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(Number tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	
	
	
}
