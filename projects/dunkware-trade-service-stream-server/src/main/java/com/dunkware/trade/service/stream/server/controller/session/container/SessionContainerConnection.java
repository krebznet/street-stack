package com.dunkware.trade.service.stream.server.controller.session.container;

import com.dunkware.spring.channel.Channel;

public interface SessionContainerConnection {
	
	Channel getChannel();
	
	void dispose(); 
	
	

}
