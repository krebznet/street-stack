package com.dunkware.trade.service.beach.protocol.trade;

import com.dunkware.trade.service.beach.protocol.trade.spec.BeachOrderSpec;

public class BeachOrderSubmitResp {

	private String error; 
	private String code; 
	private BeachOrderSpec order;
	
	public BeachOrderSubmitResp() { 
		
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BeachOrderSpec getOrder() {
		return order;
	}
	public void setOrder(BeachOrderSpec order) {
		this.order = order;
	} 
	
	
}
