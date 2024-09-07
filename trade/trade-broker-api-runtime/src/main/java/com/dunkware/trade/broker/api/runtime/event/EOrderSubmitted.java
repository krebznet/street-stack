package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderSubmitted extends EOrderUpdate  {

	public EOrderSubmitted(Order order) {
		super(order);
	}

}
