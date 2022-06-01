package com.dunkware.trade.sdk.lib.model.system;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class MatcherSystemType extends SystemType  {

	private String matcherJson; 
	private String stream;
	private String containerURL; 
	private double tradeAllocation;
	private int maxOpenTrades; 
	private int tickerTimeout; 
	private int maxOpenTickerTrades; 
	private EntryType entyType; 
	private ExitType exitType;
	
	public String getMatcherJson() {
		return matcherJson;
	}
	public void setMatcherJson(String matcherJson) {
		this.matcherJson = matcherJson;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getContainerURL() {
		return containerURL;
	}
	public void setContainerURL(String containerURL) {
		this.containerURL = containerURL;
	}
	public double getTradeAllocation() {
		return tradeAllocation;
	}
	public void setTradeAllocation(double tradeAllocation) {
		this.tradeAllocation = tradeAllocation;
	}
	public int getMaxOpenTrades() {
		return maxOpenTrades;
	}
	public void setMaxOpenTrades(int maxOpenTrades) {
		this.maxOpenTrades = maxOpenTrades;
	}
	public int getTickerTimeout() {
		return tickerTimeout;
	}
	public void setTickerTimeout(int tickerTimeout) {
		this.tickerTimeout = tickerTimeout;
	}
	public int getMaxOpenTickerTrades() {
		return maxOpenTickerTrades;
	}
	public void setMaxOpenTickerTrades(int maxOpenTickerTrades) {
		this.maxOpenTickerTrades = maxOpenTickerTrades;
	}
	public EntryType getEntyType() {
		return entyType;
	}
	public void setEntyType(EntryType entyType) {
		this.entyType = entyType;
	}
	public ExitType getExitType() {
		return exitType;
	}
	public void setExitType(ExitType exitType) {
		this.exitType = exitType;
	} 
	// 
	
	
}
