 package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.session.BeachSessionTradeOrder;

public class EBeachSessionOrderEvent extends DEvent {
	
	private BeachSessionTradeOrder order;

	public EBeachSessionOrderEvent(BeachSessionTradeOrder order) { 
		this.order = order; 
	}
	
	public BeachSessionTradeOrder getOrder() { 
		return order;
	}
}
