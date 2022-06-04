package com.dunkware.trade.tick.service.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.core.anot.ANetChannelService;
import com.dunkware.net.core.channel.NetChannel;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.net.core.service.NetChannelRequest;
import com.dunkware.net.core.service.NetChannelResponse;
import com.dunkware.net.core.service.NetChannelService;
import com.dunkware.trade.tick.service.server.feed.FeedService;
import com.dunkware.trade.tick.service.server.feed.FeedServiceProvider;

@ANetChannelService(endpoint = "/tick/feed/provider/stats")
public class FeedProviders implements NetChannelService {

	@Autowired
	private FeedService feedService; 
	
	@Override
	public void service(NetChannelRequest req, NetChannelResponse response, NetChannel channel)
			throws NetServiceException {
		// TODO Auto-generated method stub
		// okay we are going to have a thread. every one second; 
		for (FeedServiceProvider  provider : feedService.getProviders()) {
			//NetBean bean = Factory
			// Make a call every 1 second 
		}
	}
	
	
	

	
}
