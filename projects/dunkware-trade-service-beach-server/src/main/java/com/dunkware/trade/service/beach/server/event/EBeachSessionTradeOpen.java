package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.session.BeachSessionTrade;

public class EBeachSessionTradeOpen extends EBeachSessionTradeEvent {

	public EBeachSessionTradeOpen(BeachSessionTrade trade) {
		super(trade);
	}

}
