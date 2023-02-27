package com.dunkware.trade.tick.service.server.dash;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.tick.service.server.feed.FeedService;

public class TickDashService {
	
	@Autowired
	private FeedService feedService;
	
	public TickDashSubscriptionStream subscriptionStream() { 
		TickDashSubscriptionStream stream = new TickDashSubscriptionStream();
		stream.start(feedService);
		return stream;
	}

}
