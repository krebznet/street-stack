package com.dunkware.trade.service.beach.server.context.open;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.stopwatch.DTimer;
import com.dunkware.trade.service.beach.server.context.BeachTradeOpen;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class BeachTradeMarketEntry implements BeachTradeOpen {

	
	private DTimer timeoutTimer; 

	private BeachTrade trade; 
	private DEventNode eventNode;
	
	
	@Override
	public void start(BeachTrade trade) {
		this.trade = trade;
		eventNode = trade.getEventNode().createChild("/entry");
	
		
	}
	
	@Override
	public DEventNode getEventNode() {
		return eventNode; 
	}



	
}
