package com.dunkware.spring.test.server.feed;

import com.dunkware.spring.cluster.anot.ADunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetComponent;
import com.dunkware.spring.test.model.feed.FeedRequest;

@ADunkNetComponent
public class FeedNetService {

	
	
	@ADunkNetChannel(label = "Feeed Channel")
	public FeedChannel feedChannel(FeedRequest request) { 
		FeedChannel channel = new FeedChannel();
		return channel;
	}
	
	
}
