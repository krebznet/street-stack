package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderSubmitted extends EOrderUpdate  {

	public EOrderSubmitted(Order order) {
		super(order);
	}

}
