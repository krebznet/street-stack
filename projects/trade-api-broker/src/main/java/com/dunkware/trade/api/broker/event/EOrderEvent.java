package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;
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
