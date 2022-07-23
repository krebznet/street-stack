package com.dunkware.trade.service.stream.server.controller.session.container.connector;

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
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.netstream.GNetClientConnectRequest;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerConnector;

public class KafkaStreamConnector  implements SessionContainerConnector, DKafkaByteHandler2 {

	private String brokers; 
	private String serverTopic; 
	private String clientTopic; 
	private String identifier; 
	
	private DKafkaByteConsumer2 clientMessageConsumer; 
	private DKafkaByteProducer serverMessageProducer; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<GNetClientMessage> messageQueue = new LinkedBlockingQueue<GNetClientMessage>();
	
	public KafkaStreamConnector(String identifier, String brokers, String serverTopic, String clientTopic) throws Exception { 
		this.identifier = identifier;
		try {
			serverMessageProducer = DKafkaByteProducer.newInstance(brokers, clientTopic, identifier + "_" + DUUID.randomUUID(5));
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(brokers)
					.addTopic(serverTopic).setClientAndGroup("StreamContainerCOnnection_" + DUUID.randomUUID(4),
							"StreamContainerConnection" + DUUID.randomUUID(4))
					.build();
			clientMessageConsumer = DKafkaByteConsumer2.newInstance(spec);
			clientMessageConsumer.start();
			clientMessageConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new Exception("Exception creating kafka consumer/producer for stream connector " + e.toString());
		}
	}
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GNetClientMessage message = GNetClientMessage.parseFrom(record.value());
			messageQueue.add(message);
		} catch (Exception e) {
			logger.error("Exception parding g net client message from kafka record " + e.toString());
		}
	}



	@Override
	public GNetClientConnectRequest getHandshake() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public String getIdentifier() {
		return identifier;
	}


	@Override
	public BlockingQueue<GNetClientMessage> getMessageQueue() {
		return messageQueue;
	}

	@Override
	public void sendMessage(GNetServerMessage message) {
		try {
			serverMessageProducer.sendBytes(message.toByteArray());	
		} catch (Exception e) {
			logger.error("Exception sending GNetServer message " + e.toString());
		}
		
	}

	@Override
	public boolean isConnected() {
		return true;
	
	}

	
}
