 package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachOrder;

public class EBeachOrderUpdate extends DEvent {
	
	private BeachOrder order;

	public EBeachOrderUpdate(BeachOrder order) { 
		this.order = order; 
	}
	
	public BeachOrder getOrder() { 
		return order;
	}
}
