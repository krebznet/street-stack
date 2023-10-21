package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachOrder;

public class EBeachOrderException extends EBeachOrderEvent {

	public EBeachOrderException(BeachOrder order) {
		super(order);
	}
	
	

}
