package com.dunkware.trade.service.tick.client;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedStatsSpec;

public interface TickServiceClientFeed {

	public void update(TickFeedSpec	spec) throws TickServiceClientException;
	
	public void dispose();
	
	public TickFeedStatsSpec getStats();
	
	public TickStream getTickStream();
	
	public long getConsumedTickCount();
	
	
	
}
