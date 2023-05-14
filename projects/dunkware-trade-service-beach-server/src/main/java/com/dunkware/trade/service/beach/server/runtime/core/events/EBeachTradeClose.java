package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeClose extends EBeachTradeEvent {

	public EBeachTradeClose(BeachTrade trade) {
		super(trade);
	}
}
