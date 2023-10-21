package com.dunkware.trade.sdk.core.runtime.order.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;

public class EOrderPreSubmitted extends EOrderUpdate  {

	public EOrderPreSubmitted(Order order) {
		super(order);
	}

}
