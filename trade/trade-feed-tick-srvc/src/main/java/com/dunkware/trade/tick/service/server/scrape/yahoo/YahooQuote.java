package com.dunkware.trade.tick.service.server.scrape.yahoo;

public class YahooQuote {
	
	private long marketCap = 0;
	private long averageVolume = 0;
	private double marketOpen = 0;
	private double eps = 0;
	
	public long getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(long marketCap) {
		this.marketCap = marketCap;
	}
	public long getAverageVolume() {
		return averageVolume;
	}
	public void setAverageVolume(long averageVolume) {
		this.averageVolume = averageVolume;
	}
	public double getMarketOpen() {
		return marketOpen;
	}
	public void setMarketOpen(double marketOpen) {
		this.marketOpen = marketOpen;
	}
	public double getEps() {
		return eps;
	}
	public void setEps(double eps) {
		this.eps = eps;
	}

}
