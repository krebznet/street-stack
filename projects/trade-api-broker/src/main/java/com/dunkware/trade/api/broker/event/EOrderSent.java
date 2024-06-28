package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderSent extends EOrderUpdate  {

	public EOrderSent(Order order) {
		super(order);
	}

}
