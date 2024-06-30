package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderFilled extends EOrderUpdate  {

	public EOrderFilled(Order order) {
		super(order);
	}

}
