package com.dunkware.net.core.service;

import com.dunkware.net.core.channel.NetChannel;

public interface NetStreamService {
	
	public void service(NetStreamRequest req, NetStreamResponse response, NetChannel channel) throws NetServiceException;

	// then it haas to set the channel fuck 
}
