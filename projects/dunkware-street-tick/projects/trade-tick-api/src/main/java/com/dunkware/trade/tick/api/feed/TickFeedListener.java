package com.dunkware.trade.tick.api.feed;

import com.dunkware.trade.tick.model.feed.TickFeedQuote;
import com.dunkware.trade.tick.model.feed.TickFeedTrade;

public interface TickFeedListener {
	
	public void onSubscription(TickFeedSubscription subscription);
	
	public void onTrade(TickFeedSubscription subscription, TickFeedTrade trade);
	
	public void onQuote(TickFeedSubscription subscription, TickFeedQuote quote);
	
	public void onSnapshot(TickFeedSubscription subscription, TickFeedSubscription sub);

}
