package com.dunkware.trade.tick.service.protocol.ticker;

import com.dunkware.common.util.json.DJson;

public class TickerListAddReq {
	
	public static final String poop = "SELECT * FROM dunkstreet.ticker_ticker where update_error is null";
	
	public static void main(String[] args) {
		TickerListAddReq req = new  TickerListAddReq();
		req.setQuery(poop);
		req.setOverride(false);
		req.setName("onward");
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
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
