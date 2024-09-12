package com.dunkware.trade.tick.model.feed;

import java.time.LocalDateTime;

public class TickFeedTrade {
	
	private String symbol; 
	private double price; 
	private int size; 
	private LocalDateTime time;
	
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
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
	

}
