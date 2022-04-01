package com.dunkware.trade.service.data.json.controller.message;

import com.dunkware.trade.service.data.json.controller.spec.StreamSpec;

public class StreamSpecUpdate extends StreamMessage {

	private StreamSpec spec;

	public StreamSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSpec spec) {
		this.spec = spec;
	} 
	
	
	
}
