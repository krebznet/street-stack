package com.dunkware.spring.messaging.channel.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.KafkaByteConsumer;
import com.dunkware.common.kafka.consumer.KafkaByteHandler;
import com.dunkware.common.kafka.producer.KafkaByteProducer;
import com.dunkware.common.spec.kafka.KafkaByteConsumerSpec;
import com.dunkware.common.spec.kafka.KafkaByteConsumerSpec.ConsumerType;
import com.dunkware.common.spec.kafka.KafkaByteConsumerSpec.OffsetType;
import com.dunkware.common.spec.kafka.KafkaByteConsumerSpecBuilder;
import com.dunkware.common.util.json.DunkJson;
import com.dunkware.common.util.uuid.DunkUUID;
import com.dunkware.spring.messaging.channel.ChannelConnector;
import com.dunkware.spring.messaging.channel.ChannelException;
import com.dunkware.spring.messaging.message.DunkMessageTransport;

public class ChannelKafkaConnector implements ChannelConnector, KafkaByteHandler  {

	private String brokers; 
	private String consumer; 
	private String producer; 
	
	private KafkaByteConsumer kafkaConsumer; 
	private KafkaByteProducer kafkaProducer; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<DunkMessageTransport> messageQueue = new LinkedBlockingQueue<DunkMessageTransport>();
	
	public ChannelKafkaConnector(String brokers, String consumer, String producer) { 
		this.brokers = brokers;
		this.consumer = consumer; 
		this.producer = producer; 
	}
	
	@Override
	public void send(DunkMessageTransport transport) throws ChannelException {
		try {
			String serialized = DunkJson.serialize(transport);
			byte[] bytes = serialized.getBytes();
			kafkaProducer.sendBytes(bytes);
				
		} catch (Exception e) {
			logger.error("Send Message Kafka Transport Exception " + e.toString());
		}
		
	}

	@Override
	public BlockingQueue<DunkMessageTransport> consume() {
		return messageQueue;
	}

	@Override
	public void connect() throws ChannelException {
		try {
			KafkaByteConsumerSpec spec = KafkaByteConsumerSpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(brokers).addTopic(consumer)
					.setClientAndGroup("DataContainerCluster_" + DunkUUID.randomUUID(4),
							"ChannelConsumer" + DunkUUID.randomUUID(4))
					.build();
			kafkaConsumer = KafkaByteConsumer.newInstance(spec);
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new ChannelException("Exception creating kafka consumer " + e.toString());
		}

		// create producer
		try {
			kafkaProducer = KafkaByteProducer.newInstance(brokers, producer, "Channel" + DunkUUID.randomUUID(5));
		} catch (Exception e) {
			kafkaConsumer.dispose();
			throw new ChannelException("Exception Creating Channel Producer " + e.toString());
		}

	}

	@Override
	public void disconnect() {
		kafkaConsumer.dispose();
		kafkaProducer.dispose();
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			DunkMessageTransport transport = DunkJson.getObjectMapper().readValue(record.value(), DunkMessageTransport.class);
			messageQueue.add(transport);
		} catch (Exception e) {
			logger.error("Exception deserialziing message transport from kafka on channel connector "  + " "
					+ e.toString());
		}
		
	}
	
	

}
