package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Order;

public class EOrderPreSubmitted extends EOrderUpdate  {

	public EOrderPreSubmitted(Order order) {
		super(order);
	}

}
