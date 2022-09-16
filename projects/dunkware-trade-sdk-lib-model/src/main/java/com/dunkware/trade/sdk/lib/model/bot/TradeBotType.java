package com.dunkware.trade.sdk.lib.model.bot;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public class TradeBotType extends SystemType {
	
	private String name; 
	private String account; 
	private double allocatedCapital; 
	private List<TradeBotPlayType> plays = new ArrayList<TradeBotPlayType>();
	private boolean enabled; 
	private int symbolActiveLimit = 0;
	private int symbolThrottle = 0;
	private int symbolLimit = 0;
	private int tradeThrottle = 0;
	private int tradeLimit = 0; 
	private int tradeActiveLimit = 0; 
	private String stream; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Number getAllocatedCapital() {
		return allocatedCapital;
	}
	
	public List<TradeBotPlayType> getPlays() {
		return plays;
	}
	public void setPlays(List<TradeBotPlayType> plays) {
		this.plays = plays;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getSymbolActiveLimit() {
		return symbolActiveLimit;
	}
	public void setSymbolActiveLimit(int symbolActiveLimit) {
		this.symbolActiveLimit = symbolActiveLimit;
	}
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public int getSymbolLimit() {
		return symbolLimit;
	}
	public void setSymbolLimit(int symbolLimit) {
		this.symbolLimit = symbolLimit;
	}
	public int getTradeThrottle() {
		return tradeThrottle;
	}
	public void setTradeThrottle(int tradeThrottle) {
		this.tradeThrottle = tradeThrottle;
	}
	public int getTradeLimit() {
		return tradeLimit;
	}
	public void setTradeLimit(int tradeLimit) {
		this.tradeLimit = tradeLimit;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public int getTradeActiveLimit() {
		return tradeActiveLimit;
	}
	public void setTradeActiveLimit(int tradeActiveLimit) {
		this.tradeActiveLimit = tradeActiveLimit;
	}
	
	
	
	

	
	
	

	
	
	

}
