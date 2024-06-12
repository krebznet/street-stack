package com.dunkware.spring.messaging.channel.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DunkExecutor;
import com.dunkware.spring.messaging.channel.Channel;
import com.dunkware.spring.messaging.channel.ChannelException;
import com.dunkware.spring.messaging.channel.ChannelService;
import com.dunkware.spring.messaging.channel.anot.AChannelHandler;

@Service()
public class ChannelServiceImpl implements ChannelService {

	
	@Autowired
	private ApplicationContext ac; 
	
	Reflections reflections = null;
	private List<AnnotatedChannelHandler> annotatedChannelHandlers = new ArrayList<AnnotatedChannelHandler>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean loaded = false; 
	
	private void load() { 
		reflections = new Reflections("com.dunkware");
		Set<Class<?>> handlers = reflections.getTypesAnnotatedWith(AChannelHandler.class);
		for (Class<?> handlerClass : handlers) {
			AChannelHandler[] nots = handlerClass.getAnnotationsByType(AChannelHandler.class);
			if(nots.length == 0) { 
				logger.error("Parsing a channel handler witout returning the annotation " + handlerClass.getName());
				continue;
			}
			AnnotatedChannelHandler handler = new AnnotatedChannelHandler();
			handler.setChannelType(nots[0].value());
			handler.setClazz(handlerClass);
			annotatedChannelHandlers.add(handler);
		}
		
		loaded = true; 
	}
	
	
	@Override
	public Channel createChannel(String type, String broker, String consumerTopic, String producerTopic,
			Map<String,Object> injectables) throws ChannelException {
		if(!loaded) { 
			load();
		}
		
		
		ChannelImpl channel = new ChannelImpl(); 
		ac.getAutowireCapableBeanFactory().autowireBean(channel);
		DunkExecutor executor = new DunkExecutor(5);
		channel.start(executor, type, broker, consumerTopic, producerTopic, injectables, getChannelHandlerClasses(type));
		
		return channel;
	}
	
	
	
	@Override
	public List<Class<?>> getChannelHandlerClasses(String channelType) {
		if(!loaded) { 
			load();
		}
		List<Class<?>> results = new ArrayList<Class<?>>();
		for (AnnotatedChannelHandler annotatedChannelHandler : annotatedChannelHandlers) {
			if(annotatedChannelHandler.getChannelType().equals(channelType)) { 
				results.add(annotatedChannelHandler.getClazz());
			}
		}
		return results;
	}



	private class AnnotatedChannelHandler { 
		
		private Class<?> clazz; 
		private String channelType;
		public Class<?> getClazz() {
			return clazz;
		}
		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}
		public String getChannelType() {
			return channelType;
		}
		public void setChannelType(String channelType) {
			this.channelType = channelType;
		} 
		
		
	}
	

}
