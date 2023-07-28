package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachOrder;

public class EBeachOrderFilled extends EBeachOrderEvent {

	public EBeachOrderFilled(BeachOrder order) {
		super(order);
	}

}
