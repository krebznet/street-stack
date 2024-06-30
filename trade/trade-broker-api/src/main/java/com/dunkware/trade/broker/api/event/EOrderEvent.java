package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;
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
