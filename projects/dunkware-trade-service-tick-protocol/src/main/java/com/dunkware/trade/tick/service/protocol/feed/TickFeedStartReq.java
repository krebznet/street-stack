package com.dunkware.trade.tick.service.protocol.feed;

import java.util.List;

import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickFeedStartReq {
	
	private TickFeedSpec spec;

	public TickFeedStartReq() { 
		
	}
	
	public TickFeedSpec getSpec() {
		return spec;
	}

	public void setSpec(TickFeedSpec spec) {
		this.spec = spec;
	} 
	
	

}
