package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.session.BeachSessionTradeOrder;

public class EBeachOrderFilled extends EBeachSessionOrderEvent {

	public EBeachOrderFilled(BeachSessionTradeOrder order) {
		super(order);
	}

}
