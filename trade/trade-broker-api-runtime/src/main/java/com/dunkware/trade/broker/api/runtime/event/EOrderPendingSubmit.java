package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderPendingSubmit extends EOrderUpdate  {

	public EOrderPendingSubmit(Order order) {
		super(order);
	}

}
