package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderFilled extends EOrderUpdate  {

	public EOrderFilled(Order order) {
		super(order);
	}

}
