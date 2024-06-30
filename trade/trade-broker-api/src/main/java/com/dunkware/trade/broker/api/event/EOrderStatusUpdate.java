package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderStatusUpdate extends EOrderUpdate  {

	public EOrderStatusUpdate(Order order) {
		super(order);
	}

}
