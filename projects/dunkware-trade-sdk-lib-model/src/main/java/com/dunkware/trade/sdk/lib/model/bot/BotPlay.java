package com.dunkware.trade.sdk.lib.model.bot;

public class BotPlay {
	
	private String name; 
	private BotTradeSide tradeSide; 
	private Number capital;
	private Number activeTradeLimit; 
	private Number activeSymbolLimit; 
	private Number symbolThrottle;
	private Number tradeThrottle; 
	private String scanner; 
	private BotEntryStrategy entryStrategy; 
	private BotExitStrategy exitStrategy;
	private Number rank; 
	private boolean rankEnabled;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BotTradeSide getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(BotTradeSide tradeSide) {
		this.tradeSide = tradeSide;
	}
	public Number getCapital() {
		return capital;
	}
	public void setCapital(Number capital) {
		this.capital = capital;
	}
	public Number getActiveTradeLimit() {
		return activeTradeLimit;
	}
	public void setActiveTradeLimit(Number activeTradeLimit) {
		this.activeTradeLimit = activeTradeLimit;
	}
	public Number getActiveSymbolLimit() {
		return activeSymbolLimit;
	}
	public void setActiveSymbolLimit(Number activeSymbolLimit) {
		this.activeSymbolLimit = activeSymbolLimit;
	}
	public Number getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(Number symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public Number getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(Number tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	public String getScanner() {
		return scanner;
	}
	public void setScanner(String scanner) {
		this.scanner = scanner;
	}
	public BotEntryStrategy getEntryStrategy() {
		return entryStrategy;
	}
	public void setEntryStrategy(BotEntryStrategy entryStrategy) {
		this.entryStrategy = entryStrategy;
	}
	public BotExitStrategy getExitStrategy() {
		return exitStrategy;
	}
	public void setExitStrategy(BotExitStrategy exitStrategy) {
		this.exitStrategy = exitStrategy;
	}
	public Number getRank() {
		return rank;
	}
	public void setRank(Number rank) {
		this.rank = rank;
	}
	public boolean isRankEnabled() {
		return rankEnabled;
	}
	public void setRankEnabled(boolean rankEnabled) {
		this.rankEnabled = rankEnabled;
	} 
	
	
	

}
