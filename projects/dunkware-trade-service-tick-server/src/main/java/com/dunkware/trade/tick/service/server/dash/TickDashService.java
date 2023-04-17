package com.dunkware.trade.tick.service.server.dash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.tick.service.server.feed.FeedService;

@Service
public class TickDashService {
	
	@Autowired
	private FeedService feedService;
	
	public TickDashSubscriptionStream subscriptionStream() { 
		TickDashSubscriptionStream stream = new TickDashSubscriptionStream();
		stream.start(feedService);
		return stream;
	}

}
