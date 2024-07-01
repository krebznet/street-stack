package com.dunkware.street.client.link.runtime;

import com.dunkware.street.client.trade.StreetTrader;

public class StreetLink {
	
	private static StreetLink instance = null;
	
	public static StreetLink get() { 
		if(instance == null) { 
			instance = new StreetLink();
		}
		return instance; 
	}
	
	private StreetTrader trader = null;
	
	public void setTrader(StreetTrader trader) { 
		this.trader = trader;
	}
	
	public StreetTrader getTrader() throws Exception { 
		if(trader == null) { 
			throw new Exception("Street Link Not Connected");
		}
		return trader; 
	}
	
	
	
}
