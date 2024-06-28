package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderCancelled extends EOrderUpdate  {

	public EOrderCancelled(Order order) {
		super(order);
	}

}
