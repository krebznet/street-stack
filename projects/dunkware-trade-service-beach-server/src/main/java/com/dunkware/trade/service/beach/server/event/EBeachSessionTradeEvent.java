package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.session.BeachSessionTrade;

public class EBeachSessionTradeEvent extends DEvent {

	private BeachSessionTrade trade;
	
	public EBeachSessionTradeEvent(BeachSessionTrade trade) { 
		this.trade = trade;
	}
	
	public BeachSessionTrade getTrade() { 
		return trade; 
	}
}
