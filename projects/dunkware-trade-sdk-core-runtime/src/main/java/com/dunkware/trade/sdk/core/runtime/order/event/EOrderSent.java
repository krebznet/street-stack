package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderSent extends EOrderUpdate  {

	public EOrderSent(Order order) {
		super(order);
	}

}
