package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderException extends EOrderUpdate  {

	public EOrderException(Order order) {
		super(order);
	}

}
