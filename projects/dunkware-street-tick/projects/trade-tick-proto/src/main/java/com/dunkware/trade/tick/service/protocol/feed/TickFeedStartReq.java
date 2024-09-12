package com.dunkware.trade.tick.service.protocol.feed;

import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;

public class TickFeedStartReq {
	
	private TickConsumerSpec spec;

	public TickFeedStartReq() { 
		
	}
	
	public TickConsumerSpec getSpec() {
		return spec;
	}

	public void setSpec(TickConsumerSpec spec) {
		this.spec = spec;
	} 
	
	

}
