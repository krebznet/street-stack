package com.dunkware.spring.channel;

import com.dunkware.spring.message.Message;
import com.dunkware.spring.message.MessageInterceptor;

public interface Channel {
	
	void dispose();
	
	void send(Object payload) throws ChannelException;
	
	void send(Message message) throws ChannelException;
	
	Message sendReply(Object payload) throws ChannelException;
	
	void addHandler(Object annotedHandler) throws ChannelException; 
	
	void removeHandler(Object annotatedHandler) throws ChannelException;

	void addMessageInterceptor(MessageInterceptor interceptor);
	
	void removeMessageInterceptor(MessageInterceptor interceptor); 
	
	
	// Application Context 
	

}
