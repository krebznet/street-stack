package com.dunkware.trade.broker.api.runtime;

import com.dunkware.trade.broker.api.model.order.OrderKind;
import com.dunkware.trade.broker.api.model.order.OrderPreview;
import com.dunkware.trade.broker.api.model.order.OrderType;

public interface OrderExecutor {

	Order createOrder(OrderType spec) throws OrderException;

	OrderPreview createOrderPreview(OrderKind type) throws OrderException;

}
