package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderPendingSubmit extends EOrderUpdate  {

	public EOrderPendingSubmit(Order order) {
		super(order);
	}

}
