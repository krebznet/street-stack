package com.dunkware.spring.messaging.channel.support;

import java.util.concurrent.BlockingQueue;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.spring.messaging.channel.ChannelEndpoint;
import com.dunkware.spring.messaging.channel.ChannelException;
import com.dunkware.spring.messaging.message.DunkMessage;

public class KafkaChannelConnector implements ChannelEndpoint {
	
	private DKafkaByteConsumer2 consumer; 
	private DKafkaByteProducer producer; 
	
	public void connect(String brokers, String consumerTopic, String producerTopic) throws ChannelException { 
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void send(DunkMessage message) throws ChannelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BlockingQueue<DunkMessage> consumerQueue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	

}
