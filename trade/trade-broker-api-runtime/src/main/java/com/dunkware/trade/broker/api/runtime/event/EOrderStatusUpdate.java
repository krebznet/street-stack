package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderStatusUpdate extends EOrderUpdate  {

	public EOrderStatusUpdate(Order order) {
		super(order);
	}

}
