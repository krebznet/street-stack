package com.dunkware.trade.tick.service.protocol.ticker;

public class TickerListAddReq {
	
	private String name; 
	private String query;
	private boolean override; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isOverride() {
		return override;
	}
	public void setOverride(boolean override) {
		this.override = override;
	} 
	
	
	
	
	
	

}
