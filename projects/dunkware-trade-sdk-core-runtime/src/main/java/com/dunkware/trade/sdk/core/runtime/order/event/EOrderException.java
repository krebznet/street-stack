package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderException extends EOrderUpdate  {

	public EOrderException(Order order) {
		super(order);
	}

}
