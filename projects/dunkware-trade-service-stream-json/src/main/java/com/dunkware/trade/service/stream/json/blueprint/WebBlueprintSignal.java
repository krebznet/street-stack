package com.dunkware.trade.service.stream.json.blueprint;

import com.dunkware.trade.service.stream.json.query.WebStreamQuery;

public class WebBlueprintSignal {
	
	private WebStreamQuery query; 
	private String name; 
	private boolean enableSymbolThrottle; 
	private int symbolThrottle; 
	private boolean enableSymbolLimit; 
	private int symbolLimit;
	public WebStreamQuery getQuery() {
		return query;
	}
	public void setQuery(WebStreamQuery query) {
		this.query = query;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnableSymbolThrottle() {
		return enableSymbolThrottle;
	}
	public void setEnableSymbolThrottle(boolean enableSymbolThrottle) {
		this.enableSymbolThrottle = enableSymbolThrottle;
	}
	public int getSymbolThrottle() {
		return symbolThrottle;
	}
	public void setSymbolThrottle(int symbolThrottle) {
		this.symbolThrottle = symbolThrottle;
	}
	public boolean isEnableSymbolLimit() {
		return enableSymbolLimit;
	}
	public void setEnableSymbolLimit(boolean enableSymbolLimit) {
		this.enableSymbolLimit = enableSymbolLimit;
	}
	public int getSymbolLimit() {
		return symbolLimit;
	}
	public void setSymbolLimit(int symbolLimit) {
		this.symbolLimit = symbolLimit;
	} 
	
	

}
