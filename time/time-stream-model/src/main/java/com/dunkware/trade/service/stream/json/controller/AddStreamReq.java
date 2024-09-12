package com.dunkware.trade.service.stream.json.controller;

import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;

public class AddStreamReq {
	
	private StreamControllerSpec spec;

	public StreamControllerSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamControllerSpec spec) {
		this.spec = spec;
	}
	
	

}
