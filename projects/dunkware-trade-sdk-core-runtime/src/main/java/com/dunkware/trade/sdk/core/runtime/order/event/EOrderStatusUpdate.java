package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderStatusUpdate extends EOrderUpdate  {

	public EOrderStatusUpdate(Order order) {
		super(order);
	}

}
