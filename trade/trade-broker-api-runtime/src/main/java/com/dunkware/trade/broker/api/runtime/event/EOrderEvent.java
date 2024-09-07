package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;
import com.dunkware.utils.core.events.DunkEvent;

public class EOrderEvent extends DunkEvent  {

	private Order order; 
	
	public EOrderEvent(Order order) { 
		this.order = order;
	}
	
	public Order getOrder() { 
		return order;
	}
}
