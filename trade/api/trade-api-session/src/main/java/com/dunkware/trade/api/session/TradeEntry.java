package com.dunkware.trade.api.session;

import com.dunkware.utils.core.events.DunkEventNode;

public interface TradeEntry {
	
	DunkEventNode getEventNode();
	
	public void start(); 
	
	public void cancel();

	
}



// StreetBot

// implements that --> Signal -> 