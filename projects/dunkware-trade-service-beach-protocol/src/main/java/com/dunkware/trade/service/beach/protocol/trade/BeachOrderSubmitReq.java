package com.dunkware.trade.service.beach.protocol.trade;

import com.dunkware.trade.sdk.core.model.order.OrderType;

public class BeachOrderSubmitReq {

	private String broker; 
	private String account; 
	private OrderType order;
	
	public BeachOrderSubmitReq() { 
		
	}
	
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public OrderType getOrder() {
		return order;
	}
	public void setOrder(OrderType order) {
		this.order = order;
	} 
	
	
	
}
