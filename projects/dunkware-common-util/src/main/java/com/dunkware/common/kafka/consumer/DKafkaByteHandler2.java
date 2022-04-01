package com.dunkware.common.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface DKafkaByteHandler2 {
	
	public void record(ConsumerRecord<String,byte[]> record);

}
