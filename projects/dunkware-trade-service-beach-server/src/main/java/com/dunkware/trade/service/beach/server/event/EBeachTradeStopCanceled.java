package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeStopCanceled extends EBeachTradeEvent  {

	public EBeachTradeStopCanceled(BeachTrade trade) {
		super(trade);
	}

	
}
