package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderCreated extends EOrderUpdate  {

	public EOrderCreated(Order order) {
		super(order);
	}

}
