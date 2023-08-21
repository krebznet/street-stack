package com.dunkware.spring.messaging.channel;

import com.dunkware.spring.messaging.message.DunkMessage;

public interface ChannelInterceptor {
	
	
	boolean intercept(Channel channel, DunkMessage message);

}
