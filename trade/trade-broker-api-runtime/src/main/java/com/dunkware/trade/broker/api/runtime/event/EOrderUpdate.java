package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderUpdate extends EOrderEvent  {

	public EOrderUpdate(Order order) {
		super(order);
	}

}
