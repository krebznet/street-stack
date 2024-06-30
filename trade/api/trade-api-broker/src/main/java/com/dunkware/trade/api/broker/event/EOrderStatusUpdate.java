package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderStatusUpdate extends EOrderUpdate  {

	public EOrderStatusUpdate(Order order) {
		super(order);
	}

}
