package com.dunkware.trade.tick.service.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.anot.AClusterNetStreamService;
import com.dunkware.net.core.channel.NetChannel;
import com.dunkware.net.core.data.NetBean;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.net.core.service.NetStreamRequest;
import com.dunkware.net.core.service.NetStreamResponse;
import com.dunkware.net.core.service.NetStreamService;
import com.dunkware.trade.tick.service.server.feed.FeedService;
import com.dunkware.trade.tick.service.server.feed.FeedServiceProvider;

@AClusterNetStreamService(endpoint = "/tick/feed/provider/stats")
public class FeedProviders implements NetStreamService {

	@Autowired
	private FeedService feedService; 
	
	@Override
	public void service(NetStreamRequest req, NetStreamResponse response, NetChannel channel)
			throws NetServiceException {
		// TODO Auto-generated method stub
		// okay we are going to have a thread. every one second; 
		for (FeedServiceProvider  provider : feedService.getProviders()) {
			//NetBean bean = Factory
			// Make a call every 1 second 
		}
	}
	
	
	

	
}
