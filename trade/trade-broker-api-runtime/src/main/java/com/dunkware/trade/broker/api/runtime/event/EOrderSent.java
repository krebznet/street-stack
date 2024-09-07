package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderSent extends EOrderUpdate  {

	public EOrderSent(Order order) {
		super(order);
	}

}
