package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderFilled extends EOrderUpdate  {

	public EOrderFilled(Order order) {
		super(order);
	}

}
