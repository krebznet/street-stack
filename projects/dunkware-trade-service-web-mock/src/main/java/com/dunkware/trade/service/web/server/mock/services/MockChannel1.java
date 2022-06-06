package com.dunkware.trade.service.web.server.mock.services;

import com.dunkware.net.core.channel.NetChannel;
import com.dunkware.net.core.service.NetChannelRequest;
import com.dunkware.net.core.service.NetChannelResponse;
import com.dunkware.net.core.service.NetChannelService;
import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.trade.service.web.server.mock.MockServiceStub;

public class MockChannel1 implements NetChannelService, MockServiceStub  {

	@Override
	public String getEndpoint() {
		return "/mock/channel/1";
	
	}

	@Override
	public void service(NetChannelRequest req, NetChannelResponse response, NetChannel channel)
			throws NetServiceException {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
