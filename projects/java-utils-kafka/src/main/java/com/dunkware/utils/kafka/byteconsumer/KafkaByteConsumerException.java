package com.dunkware.utils.kafka.byteconsumer;

public class KafkaByteConsumerException extends Exception {

	
	public KafkaByteConsumerException(String s) {
		super(s);
		
	}
	
	public KafkaByteConsumerException(String s, Throwable t) {
		super(s,t);
	}
}
