package com.dunkware.trade.service.stream.json.blueprint;

import com.dunkware.trade.service.stream.json.query.WebStreamQuery;

public class WebStreamSignaltype {
	
	private WebStreamQuery query; 
	private String name; 
	private boolean enableSymbolThrottle; 
	private int symbolThrottle; 
	private boolean enableSymbolLimit; 
	private int symbolLimit;
	private String description; 
	private String startTime; 
	private String stopTime; 
	private boolean enableTimeWindow; 
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public boolean isEnableTimeWindow() {
		return enableTimeWindow;
	}
	public void setEnableTimeWindow(boolean enableTimeWindow) {
		this.enableTimeWindow = enableTimeWindow;
	} 
	
	
	

}
