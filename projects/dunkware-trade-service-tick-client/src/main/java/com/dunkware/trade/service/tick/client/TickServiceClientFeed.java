package com.dunkware.trade.service.tick.client;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;

public interface TickServiceClientFeed {

	public void update(TickConsumerSpec	spec) throws TickServiceClientException;
	
	public void dispose();
	
	public TickConsumerSpec getSpec();
	
	public TickStream getTickStream();
	
	public long getConsumedTickCount();
	
	
	
}
