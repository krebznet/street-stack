package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Order;

public class EOrderPreSubmitted extends EOrderUpdate  {

	public EOrderPreSubmitted(Order order) {
		super(order);
	}

}
