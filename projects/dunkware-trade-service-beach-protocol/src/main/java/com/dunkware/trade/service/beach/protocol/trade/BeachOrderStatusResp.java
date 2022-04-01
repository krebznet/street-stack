package com.dunkware.trade.service.beach.protocol.trade;

import com.dunkware.trade.sdk.core.model.order.OrderSpec;

public class BeachOrderStatusResp {
	
	private String code; 
	private String error; 
	
	private String account; 
	private String broker; 
	
	private OrderSpec spec;
	
	public BeachOrderStatusResp() { 
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public OrderSpec getSpec() {
		return spec;
	}

	public void setSpec(OrderSpec spec) {
		this.spec = spec;
	}
	
	

}
