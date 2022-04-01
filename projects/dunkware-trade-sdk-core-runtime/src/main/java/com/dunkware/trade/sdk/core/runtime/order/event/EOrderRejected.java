package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderRejected extends EOrderUpdate  {

	public EOrderRejected(Order order) {
		super(order);
	}

}
