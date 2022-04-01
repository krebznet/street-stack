package com.dunkware.trade.service.data.json.controller.message;

import com.dunkware.trade.service.data.json.controller.spec.StreamSessionSpec;

public class StreamSessionPing extends StreamMessage {

	private StreamSessionSpec spec;

	public StreamSessionSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionSpec spec) {
		this.spec = spec;
	}
	
}
