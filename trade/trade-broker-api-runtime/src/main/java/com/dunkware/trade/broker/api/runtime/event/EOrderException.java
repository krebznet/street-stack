package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderException extends EOrderUpdate  {

	public EOrderException(Order order) {
		super(order);
	}

}
