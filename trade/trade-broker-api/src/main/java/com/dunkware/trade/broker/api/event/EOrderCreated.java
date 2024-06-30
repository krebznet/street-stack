package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderCreated extends EOrderUpdate  {

	public EOrderCreated(Order order) {
		super(order);
	}

}
