package com.dunkware.spring.channel.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.channel.ChannelConnector;
import com.dunkware.spring.channel.ChannelException;
import com.dunkware.spring.message.MessageTransport;

public class ChannelKafkaConnector implements ChannelConnector, DKafkaByteHandler2  {

	private String brokers; 
	private String consumer; 
	private String producer; 
	
	private DKafkaByteConsumer2 kafkaConsumer; 
	private DKafkaByteProducer kafkaProducer; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<MessageTransport> messageQueue = new LinkedBlockingQueue<MessageTransport>();
	
	public ChannelKafkaConnector(String brokers, String consumer, String producer) { 
		this.brokers = brokers;
		this.consumer = consumer; 
		this.producer = producer; 
	}
	
	@Override
	public void send(MessageTransport transport) throws ChannelException {
		try {
			String serialized = DJson.serialize(transport);
			byte[] bytes = serialized.getBytes();
			kafkaProducer.sendBytes(bytes);
				
		} catch (Exception e) {
			logger.error("Send Message Kafka Transport Exception " + e.toString());
		}
		
	}

	@Override
	public BlockingQueue<MessageTransport> consume() {
		return messageQueue;
	}

	@Override
	public void connect() throws ChannelException {
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(brokers).addTopic(consumer)
					.setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
							"ChannelConsumer" + DUUID.randomUUID(4))
					.build();
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new ChannelException("Exception creating kafka consumer " + e.toString());
		}

		// create producer
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(brokers, producer, "Channel" + DUUID.randomUUID(5));
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
			MessageTransport transport = DJson.getObjectMapper().readValue(record.value(), MessageTransport.class);
			messageQueue.add(transport);
		} catch (Exception e) {
			logger.error("Exception deserialziing message transport from kafka on channel connector "  + " "
					+ e.toString());
		}
		
	}
	
	

}
