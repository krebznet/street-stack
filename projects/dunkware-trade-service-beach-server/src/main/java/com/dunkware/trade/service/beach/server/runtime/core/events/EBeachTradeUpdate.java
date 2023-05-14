package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeUpdate extends EBeachTradeEvent {

	public EBeachTradeUpdate(BeachTrade trade) {
		super(trade);
	}
}
