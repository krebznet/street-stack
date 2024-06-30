package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderPendingSubmit extends EOrderUpdate  {

	public EOrderPendingSubmit(Order order) {
		super(order);
	}

}
