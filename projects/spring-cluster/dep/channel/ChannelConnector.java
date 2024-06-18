package com.dunkware.spring.messaging.channel;

import java.util.concurrent.BlockingQueue;

import com.dunkware.spring.messaging.message.DunkMessageTransport;

public interface ChannelConnector {
	
	void connect() throws ChannelException; 
	
	void disconnect(); 
	
	public void send(DunkMessageTransport transport) throws ChannelException;
	
	public BlockingQueue<DunkMessageTransport> consume(); 

}
