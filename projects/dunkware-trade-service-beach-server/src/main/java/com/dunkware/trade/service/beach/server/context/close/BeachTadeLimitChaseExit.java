package com.dunkware.trade.service.beach.server.context.close;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.server.context.BeachTradeClose;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class BeachTadeLimitChaseExit implements BeachTradeClose {

	private DEventNode eventNode; 
	private BeachTrade trade;
	
	private int chaseInterval; 
	
	@Override
	public void start(BeachTrade trade) {
		this.trade = trade;
		this.eventNode = trade.getEventNode().createChild("/exit");
		//chaseInterval = trade.getPlay().getModel().getExitChaseInterval();
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	
}
