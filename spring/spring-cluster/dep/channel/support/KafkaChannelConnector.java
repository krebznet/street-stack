package com.dunkware.spring.messaging.channel.support;

import java.util.concurrent.BlockingQueue;

import com.dunkware.common.kafka.consumer.KafkaByteConsumer;
import com.dunkware.common.kafka.producer.KafkaByteProducer;
import com.dunkware.spring.messaging.channel.ChannelEndpoint;
import com.dunkware.spring.messaging.channel.ChannelException;
import com.dunkware.spring.messaging.message.DunkMessage;

public class KafkaChannelConnector implements ChannelEndpoint {
	
	private KafkaByteConsumer consumer; 
	private KafkaByteProducer producer; 
	
	public void connect(String brokers, String consumerTopic, String producerTopic) throws ChannelException { 
		try {
			
		} catch (Exception e) {
			
		}
	}

	@Override
	public void send(DunkMessage message) throws ChannelException {
		
		
	}

	@Override
	public BlockingQueue<DunkMessage> consumerQueue() {
		
		return null;
	}

	@Override
	public void disconnect() {
		
		
	}
	
	

}
