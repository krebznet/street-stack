package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderUpdate extends EOrderEvent  {

	public EOrderUpdate(Order order) {
		super(order);
	}

}
