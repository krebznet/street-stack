package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderCancelled extends EOrderUpdate  {

	public EOrderCancelled(Order order) {
		super(order);
	}

}
