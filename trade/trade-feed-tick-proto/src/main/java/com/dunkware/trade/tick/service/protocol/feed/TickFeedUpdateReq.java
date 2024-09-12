package com.dunkware.trade.tick.service.protocol.feed;

import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;

public class TickFeedUpdateReq {

	private String id;
	private TickConsumerSpec spec;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TickConsumerSpec getSpec() {
		return spec;
	}
	public void setSpec(TickConsumerSpec spec) {
		this.spec = spec;
	} 
	
	
}
