package com.dunkware.trade.tick.provider.polygon.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonAggEvent {

	@JsonProperty(value = "ev")
	private String event; 
	@JsonProperty(value = "sym")
	private String symbol; 
	@JsonProperty(value = "v")
	private int tickVolume;
	@JsonProperty(value = "av")
	private int volume; 
	@JsonProperty(value = "op")
	private double openPrice;
	@JsonProperty(value = "vw")
	private double tickVwap; 
	@JsonProperty(value = "o")
	private double tickOpen; 
	@JsonProperty(value = "c")
	private double tickClose; 
	@JsonProperty(value = "h")
	private double tickHigh;
	@JsonProperty(value = "l")
	private double tickLow; 
	@JsonProperty(value = "a")
	private double vwap; 
	@JsonProperty(value = "z")
	private int tickAverageTradeSize;
	@JsonProperty(value = "e")
	private long endTime;
	@JsonProperty(value = "s")
	private long startTime;
	
	public PolygonAggEvent() { 
		
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getTickVolume() {
		return tickVolume;
	}
	public void setTickVolume(int tickVolume) {
		this.tickVolume = tickVolume;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getTickVwap() {
		return tickVwap;
	}
	public void setTickVwap(double tickVwap) {
		this.tickVwap = tickVwap;
	}
	public double getTickOpen() {
		return tickOpen;
	}
	public void setTickOpen(double tickOpen) {
		this.tickOpen = tickOpen;
	}
	public double getTickClose() {
		return tickClose;
	}
	public void setTickClose(double tickClose) {
		this.tickClose = tickClose;
	}
	public double getTickHigh() {
		return tickHigh;
	}
	public void setTickHigh(double tickHigh) {
		this.tickHigh = tickHigh;
	}
	public double getTickLow() {
		return tickLow;
	}
	public void setTickLow(double tickLow) {
		this.tickLow = tickLow;
	}
	public double getVwap() {
		return vwap;
	}
	public void setVwap(double vwap) {
		this.vwap = vwap;
	}
	public int getTickAverageTradeSize() {
		return tickAverageTradeSize;
	}
	public void setTickAverageTradeSize(int tickAverageTradeSize) {
		this.tickAverageTradeSize = tickAverageTradeSize;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	

	
	
}
