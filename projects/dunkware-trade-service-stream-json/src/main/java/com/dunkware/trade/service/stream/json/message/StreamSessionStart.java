package com.dunkware.trade.service.stream.json.message;

import com.dunkware.trade.service.stream.json.controller.model.StreamSessionSpec;

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
