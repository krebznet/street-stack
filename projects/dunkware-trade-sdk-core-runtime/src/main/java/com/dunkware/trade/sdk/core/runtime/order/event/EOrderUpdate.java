package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderUpdate extends EOrderEvent  {

	public EOrderUpdate(Order order) {
		super(order);
	}

}
