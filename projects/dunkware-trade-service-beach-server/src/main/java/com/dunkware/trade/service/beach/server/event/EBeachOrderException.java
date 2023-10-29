package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.session.BeachSessionTradeOrder;

public class EBeachOrderException extends EBeachSessionOrderEvent {

	public EBeachOrderException(BeachSessionTradeOrder order) {
		super(order);
	}
	
	

}
