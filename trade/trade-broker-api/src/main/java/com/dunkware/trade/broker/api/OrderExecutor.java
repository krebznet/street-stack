package com.dunkware.trade.broker.api;

public interface OrderExecutor {

	Order createOrder(OrderSpec spec) throws OrderException;

	OrderPreview createOrderPreview(OrderType type) throws OrderException;

}
