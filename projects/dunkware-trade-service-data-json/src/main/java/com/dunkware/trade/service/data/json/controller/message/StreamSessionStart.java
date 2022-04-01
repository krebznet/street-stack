package com.dunkware.trade.service.data.json.controller.message;

import com.dunkware.trade.service.data.json.controller.spec.StreamSessionSpec;

/**
 * Indicates a StreamSessionStart all the session details are in 
 * the StreamSession objects. 
 * @author duncankrebs
 *
 */
public class StreamSessionStart extends StreamMessage {
	
	private StreamSessionSpec spec;

	public StreamSessionSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionSpec spec) {
		this.spec = spec;
	}

	
	
	
	
}
