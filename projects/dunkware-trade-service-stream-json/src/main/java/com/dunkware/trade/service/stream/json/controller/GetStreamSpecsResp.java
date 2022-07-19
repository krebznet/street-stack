package com.dunkware.trade.service.stream.json.controller;

import java.util.List;

import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;

public class GetStreamSpecsResp {
	
	private String code; 
	private String error; 
	private List<StreamControllerSpec> specs;

	public List<StreamControllerSpec> getSpecs() {
		return specs;
	}

	public void setSpecs(List<StreamControllerSpec> specs) {
		this.specs = specs;
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
	
	
	

}
