package com.dunkware.trade.sdk.lib.model.streambot;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class StreamBotPlayType  {
	
	private String name; 
	private StreamBottypeSide tradeSide; 
	private double capital;
	private int activeTradeLimit; 
	private int activeSymbolLimit; 
	private int symbolThrottle;
	private int tradeThrottle; 
	private String scanner; 
	private EntryType entyType; 
	private ExitType exitType; 
	private int rank; 
	
	private boolean rankEnabled;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StreamBottypeSide getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(StreamBottypeSide tradeSide) {
		this.tradeSide = tradeSide;
	}
	public Number getCapital() {
		return capital;
	}
	public int getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public void setActiveTradeLimit(int activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public int getActiveSymbolLimit() {
		return activeSymbolLimit;
	}
	public void setActiveSymbolLimit(int activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public int getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(int tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	public String getScanner() {
		return scanner;
	}
	public void setScanner(String scanner) {
		this.scanner = scanner;
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public boolean isRankEnabled() {
		return rankEnabled;
	}
	public void setRankEnabled(boolean rankEnabled) {
		this.rankEnabled = rankEnabled;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	
	
	
	
	

}
