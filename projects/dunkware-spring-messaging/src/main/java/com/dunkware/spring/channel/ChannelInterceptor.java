package com.dunkware.spring.channel;

import com.dunkware.spring.message.Message;

public interface ChannelInterceptor {
	
	
	boolean intercept(Channel channel, Message message);

}
