package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderCreated extends EOrderUpdate  {

	public EOrderCreated(Order order) {
		super(order);
	}

}
