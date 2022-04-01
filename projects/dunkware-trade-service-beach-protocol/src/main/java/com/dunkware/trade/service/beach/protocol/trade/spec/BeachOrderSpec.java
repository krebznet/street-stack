package com.dunkware.trade.service.beach.protocol.trade.spec;

import com.dunkware.trade.sdk.core.model.order.OrderSpec;

public class BeachOrderSpec {

	private String broker;
	private String account; 
	private OrderSpec order;
	
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
	public OrderSpec getOrder() {
		return order;
	}
	public void setOrder(OrderSpec order) {
		this.order = order;
	}
	
	
}
