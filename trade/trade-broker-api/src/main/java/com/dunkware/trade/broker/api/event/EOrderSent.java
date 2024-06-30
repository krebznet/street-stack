package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderSent extends EOrderUpdate  {

	public EOrderSent(Order order) {
		super(order);
	}

}
