package com.dunkware.trade.service.beach.protocol.controller;

import com.dunkware.trade.service.beach.protocol.spec.BeachBrokerSpec;

public class BeachBrokerAddResp {

	private String code;
	private String error;
	private BeachBrokerSpec broker; 
	
	public BeachBrokerAddResp() { 
		
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

	public BeachBrokerSpec getBroker() {
		return broker;
	}

	public void setBroker(BeachBrokerSpec broker) {
		this.broker = broker;
	}
	
	

}
