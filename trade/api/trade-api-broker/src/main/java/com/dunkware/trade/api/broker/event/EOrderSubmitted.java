package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderSubmitted extends EOrderUpdate  {

	public EOrderSubmitted(Order order) {
		super(order);
	}

}
