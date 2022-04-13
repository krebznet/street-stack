package com.dunkware.trade.service.stream.json.controller;

import com.dunkware.trade.service.stream.json.controller.spec.StreamSpec;

public class GetStreamSpecResp {
	
	private String code; 
	private StreamSpec spec;
	private String error; 
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public StreamSpec getSpec() {
		return spec;
	}
	public void setSpec(StreamSpec spec) {
		this.spec = spec;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	
	

}
