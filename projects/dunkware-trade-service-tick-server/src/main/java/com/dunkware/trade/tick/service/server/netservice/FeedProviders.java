package com.dunkware.trade.tick.service.server.netservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.core.anot.ANetCallService;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetCallService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.tick.service.server.feed.FeedService;
import com.dunkware.trade.tick.service.server.feed.FeedServiceProvider;

@ANetCallService(endpoint = "/feed/provider/stats")
public class FeedProviders implements NetCallService {

	@Autowired
	private FeedService feedService;

	@Override
	public void service(NetCallRequest req, NetCallResponse resp) throws NetServiceException {
		for (FeedServiceProvider provider : feedService.getProviders()) {
			int mCount = provider.getProviderStats().getMessageCount();
			String name = provider.getProviderStats().getLastSnapshotMessage();
			resp.getNetBean().setString("name", name);
			resp.getNetBean().setInt("messageCount", mCount);
			return;
		}
	} 
	
	
	
	
	

	
}
