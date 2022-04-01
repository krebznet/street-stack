package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.Contract;
import com.ib.client.EWrapper;
import com.ib.client.TwsOrder;
import com.ib.client.TwsOrderState;

public class WCOpenOrder implements EWrapperCall {
	
	int orderId;
	Contract contract;
	TwsOrder order;
	TwsOrderState orderState;
	
	public WCOpenOrder(int orderId, Contract contract, TwsOrder order, TwsOrderState orderState) {
		this.orderId = orderId; 
		this.contract = contract; 
		this.order = order; 
		this.orderState = orderState;
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.openOrder(orderId, contract, order, orderState);
		
	}
	
	

}
