package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeEvent extends DEvent {

	private BeachTrade trade;
	
	public EBeachTradeEvent(BeachTrade trade) { 
		this.trade = trade;
	}
	
	public BeachTrade getTrade() { 
		return trade; 
	}
}
