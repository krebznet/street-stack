package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderException extends EOrderUpdate  {

	public EOrderException(Order order) {
		super(order);
	}

}
