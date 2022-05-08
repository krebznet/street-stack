package com.dunkware.trade.tick.provider.polygon.core;

public class PolygonSnapshot {
	
	private PolygonLastQuote lastQuote; 
	private PolygonLastTrade latTrade; 
	private PolygonDay day; 
	private PolygonMin min; 
	private PolygonPreviousDay prevDay; 
	
	private String ticker; 
	private double todaysChange; 
	private double todaysChangePercent; 
	private long updated;
	public PolygonLastQuote getLastQuote() {
		return lastQuote;
	}
	public void setLastQuote(PolygonLastQuote lastQuote) {
		this.lastQuote = lastQuote;
	}
	public PolygonLastTrade getLatTrade() {
		return latTrade;
	}
	public void setLatTrade(PolygonLastTrade latTrade) {
		this.latTrade = latTrade;
	}
	public PolygonDay getDay() {
		return day;
	}
	public void setDay(PolygonDay day) {
		this.day = day;
	}
	public PolygonMin getMin() {
		return min;
	}
	public void setMin(PolygonMin min) {
		this.min = min;
	}
	public PolygonPreviousDay getPrevDay() {
		return prevDay;
	}
	public void setPrevDay(PolygonPreviousDay prevDay) {
		this.prevDay = prevDay;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public double getTodaysChange() {
		return todaysChange;
	}
	public void setTodaysChange(double todaysChange) {
		this.todaysChange = todaysChange;
	}
	public double getTodaysChangePercent() {
		return todaysChangePercent;
	}
	public void setTodaysChangePercent(double todaysChangePercent) {
		this.todaysChangePercent = todaysChangePercent;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	} 
	
	

}
