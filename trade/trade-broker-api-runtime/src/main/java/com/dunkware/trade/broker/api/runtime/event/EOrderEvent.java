package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderEvent  {

	private Order order; 
	
	public EOrderEvent(Order order) { 
		this.order = order;
	}
	
	public Order getOrder() { 
		return order;
	}
}
