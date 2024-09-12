package com.dunkware.trade.service.tick.client;

import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.utils.tick.stream.TickStream;

public interface TickServiceClientFeed {

	public void update(TickConsumerSpec	spec) throws TickServiceClientException;
	
	public void dispose();
	
	public TickConsumerSpec getSpec();
	
	public TickStream getTickStream();
	
	public long getConsumedTickCount();
	
	
	
}
