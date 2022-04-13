package com.dunkware.trade.service.stream.json.message;

import com.dunkware.trade.service.stream.json.controller.model.StreamSpec;

public class StreamSpecUpdate extends StreamMessage {

	private StreamSpec spec;

	public StreamSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSpec spec) {
		this.spec = spec;
	} 
	
	
	
}
