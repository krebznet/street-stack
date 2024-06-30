package com.dunkware.spring.messaging.channel;

import java.util.concurrent.BlockingQueue;

import com.dunkware.spring.messaging.message.DunkMessage;

public interface ChannelEndpoint {

	public void send(DunkMessage message) throws ChannelException;
	
	public BlockingQueue<DunkMessage> consumerQueue();
	
	public void disconnect();
}
