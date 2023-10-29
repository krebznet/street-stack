package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.session.BeachSessionTrade;

public class EBeachSessionStopCancelled extends EBeachSessionTradeEvent  {

	public EBeachSessionStopCancelled(BeachSessionTrade trade) {
		super(trade);
	}

	
}
