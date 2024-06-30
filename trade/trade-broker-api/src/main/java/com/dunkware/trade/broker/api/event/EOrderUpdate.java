package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderUpdate extends EOrderEvent  {

	public EOrderUpdate(Order order) {
		super(order);
	}

}
