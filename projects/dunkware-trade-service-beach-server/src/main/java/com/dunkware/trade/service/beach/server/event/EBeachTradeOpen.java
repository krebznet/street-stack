package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachTrade;

public class EBeachTradeOpen extends EBeachTradeEvent {

	public EBeachTradeOpen(BeachTrade trade) {
		super(trade);
	}

}
