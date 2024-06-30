package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Order;

public class EOrderPreSubmitted extends EOrderUpdate  {

	public EOrderPreSubmitted(Order order) {
		super(order);
	}

}
