package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderCancelled extends EOrderUpdate  {

	public EOrderCancelled(Order order) {
		super(order);
	}

}
