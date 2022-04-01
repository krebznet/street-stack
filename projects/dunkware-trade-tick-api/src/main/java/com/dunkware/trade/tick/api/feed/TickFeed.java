package com.dunkware.trade.tick.api.feed;

import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.trade.tick.model.feed.TickFeedStatsSpec;
import com.dunkware.trade.tick.model.feed.TickFeedStatus;

public interface TickFeed extends TickStream {

	/**
	 * Disposes a stream and releases associated resources
	 */
	public void dispose();
	
	public TickFeedStatus getStatus();
	
	public TickFeedStatsSpec getStats();
	
	public void update(TickFeedSpec spec) throws TickFeedException; 
	
}

