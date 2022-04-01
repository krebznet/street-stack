package com.dunkware.trade.service.data.json.controller.message;

import com.dunkware.trade.service.data.json.controller.spec.StreamSessionSpec;

/**
 * Indicates a StreamSessionStop all details are in the session instance.
 * @author duncankrebs
 *
 */
public class StreamSessionStop extends StreamMessage {
	
	private StreamSessionSpec spec;

	public StreamSessionSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionSpec spec) {
		this.spec = spec;
	}
	
	
	

}
