package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderRejected extends EOrderUpdate  {

	public EOrderRejected(Order order) {
		super(order);
	}

}
