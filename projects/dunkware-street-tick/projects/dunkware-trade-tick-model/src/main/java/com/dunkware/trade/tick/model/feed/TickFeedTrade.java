package com.dunkware.trade.tick.model.feed;

import com.dunkware.common.util.dtime.DDateTime;

public class TickFeedTrade {
	
	private String symbol; 
	private double price; 
	private int size; 
	private DDateTime time;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public DDateTime getTime() {
		return time;
	}
	public void setTime(DDateTime time) {
		this.time = time;
	}
	
	
	

}
