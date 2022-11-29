package com.dunkware.spring.channel;

import java.util.concurrent.BlockingQueue;

import com.dunkware.spring.message.MessageTransport;

public interface ChannelConnector {
	
	void connect() throws ChannelException; 
	
	void disconnect(); 
	
	public void send(MessageTransport transport) throws ChannelException;
	
	public BlockingQueue<MessageTransport> consume(); 

}
