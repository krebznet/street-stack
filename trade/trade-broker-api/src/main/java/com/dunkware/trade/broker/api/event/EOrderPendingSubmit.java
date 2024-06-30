package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderPendingSubmit extends EOrderUpdate  {

	public EOrderPendingSubmit(Order order) {
		super(order);
	}

}
