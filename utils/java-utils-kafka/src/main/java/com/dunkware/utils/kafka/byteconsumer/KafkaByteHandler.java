package com.dunkware.utils.kafka.byteconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaByteHandler {
	
	public void record(ConsumerRecord<String,byte[]> record);

}
