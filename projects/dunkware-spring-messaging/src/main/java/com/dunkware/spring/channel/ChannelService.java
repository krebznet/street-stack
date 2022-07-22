package com.dunkware.spring.channel;

import java.util.List;
import java.util.Map;

public interface ChannelService {
	
	
	Channel createChannel(String type, String broker, String consumerTopic, String producerTopic, Map<String,Object> injectables) throws ChannelException;

	List<Class<?>> getChannelHandlerClasses(String channelType);
	
	

}
