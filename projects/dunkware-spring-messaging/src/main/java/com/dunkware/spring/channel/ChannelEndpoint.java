package com.dunkware.spring.channel;

import java.util.concurrent.BlockingQueue;

import com.dunkware.spring.message.Message;

public interface ChannelEndpoint {

	public void send(Message message) throws ChannelException;
	
	public BlockingQueue<Message> consumerQueue();
	
	public void disconnect();
}
