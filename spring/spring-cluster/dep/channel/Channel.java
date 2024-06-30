package com.dunkware.spring.messaging.channel;

import com.dunkware.spring.messaging.message.DunkMessage;
import com.dunkware.spring.messaging.message.DunkMessageInterceptor;

public interface Channel {
	
	void open();
	
	void dispose();
	
	void send(Object payload) throws ChannelException;
	
	void send(DunkMessage message) throws ChannelException;
	
	DunkMessage sendReply(Object payload) throws ChannelException;
	
	void addHandler(Object annotedHandler) throws ChannelException; 
	
	void removeHandler(Object annotatedHandler) throws ChannelException;

	void addMessageInterceptor(DunkMessageInterceptor interceptor);
	
	void removeMessageInterceptor(DunkMessageInterceptor interceptor); 
	
	
	// Application Context 
	

}
