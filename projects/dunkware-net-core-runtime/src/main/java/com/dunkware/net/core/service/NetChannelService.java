package com.dunkware.net.core.service;

import com.dunkware.net.core.channel.NetChannel;

public interface NetChannelService {
	
	public void service(NetChannelRequest req, NetChannelResponse response, NetChannel channel) throws NetServiceException;

	// then it haas to set the channel fuck 
}
