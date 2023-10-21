package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderFilled extends EOrderUpdate  {

	public EOrderFilled(Order order) {
		super(order);
	}

}
