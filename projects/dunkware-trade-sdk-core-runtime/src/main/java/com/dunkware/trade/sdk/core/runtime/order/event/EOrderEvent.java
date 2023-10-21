package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderEvent extends DEvent  {

	private Order order; 
	
	public EOrderEvent(Order order) { 
		this.order = order;
	}
	
	public Order getOrder() { 
		return order;
	}
}
