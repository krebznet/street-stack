package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderRejected extends EOrderUpdate  {

	public EOrderRejected(Order order) {
		super(order);
	}

}
