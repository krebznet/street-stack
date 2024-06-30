package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderRejected extends EOrderUpdate  {

	public EOrderRejected(Order order) {
		super(order);
	}

}
