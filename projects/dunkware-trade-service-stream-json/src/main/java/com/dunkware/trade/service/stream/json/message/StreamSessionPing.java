package com.dunkware.trade.service.stream.json.message;

import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;

public class StreamSessionPing extends StreamMessage {

	private StreamSessionSpec spec;

	public StreamSessionSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionSpec spec) {
		this.spec = spec;
	}
	
}
