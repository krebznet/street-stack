package com.dunkware.utils.kafka.tickconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumer;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumerSpec;
import com.dunkware.utils.kafka.byteconsumer.KafkaByteHandler;
import com.dunkware.utils.tick.proto.TickProto;
import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.stream.impl.TickStreamImpl;

public class KafkaTickStream extends TickStreamImpl implements KafkaByteHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static KafkaTickStream newInstance(KafkaByteConsumerSpec spec) throws Exception { 
		return new KafkaTickStream(spec);
	}
	
	
	private KafkaByteConsumer kafkaConsumer; 
	
	private KafkaTickStream(KafkaByteConsumerSpec model) throws Exception { 
		kafkaConsumer = KafkaByteConsumer.newInstance(model);
		kafkaConsumer.start();
		kafkaConsumer.addStreamHandler(this);
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			Tick tick = TickProto.Tick.parseFrom(record.value());
			consume(tick);
		} catch (Exception e) {
			logger.error("Kafka Tick Consumer Decode Exception " + e.toString());
		}
	}
	
	
	public void dispose() { 
		kafkaConsumer.dispose();
	}

	

}
