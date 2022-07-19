package com.dunkware.trade.service.stream.json.controller;

import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;

public class GetStreamSpecResp {
	
	private String code; 
	private StreamControllerSpec spec;
	private String error; 
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public StreamControllerSpec getSpec() {
		return spec;
	}
	public void setSpec(StreamControllerSpec spec) {
		this.spec = spec;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	
	

}
