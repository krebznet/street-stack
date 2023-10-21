package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderSubmitted extends EOrderUpdate  {

	public EOrderSubmitted(Order order) {
		super(order);
	}

}
