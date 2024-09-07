package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderCreated extends EOrderUpdate  {

	public EOrderCreated(Order order) {
		super(order);
	}

}
