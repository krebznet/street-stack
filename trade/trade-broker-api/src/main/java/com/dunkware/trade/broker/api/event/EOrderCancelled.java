package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderCancelled extends EOrderUpdate  {

	public EOrderCancelled(Order order) {
		super(order);
	}

}
