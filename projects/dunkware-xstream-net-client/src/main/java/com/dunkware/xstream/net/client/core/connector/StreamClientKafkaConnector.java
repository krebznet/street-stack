package com.dunkware.xstream.net.client.core.connector;

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
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.netstream.GNetClientConnectResponse;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;
import com.dunkware.xstream.net.client.connector.StreamClientKafkaConnectorType;
import com.dunkware.xstream.net.service.KafkaStreamClientRequest;
import com.dunkware.xstream.net.service.KafkaStreamClientResponse;

public class StreamClientKafkaConnector implements StreamClientConnector, DKafkaByteHandler2 {

	private StreamClientKafkaConnectorType myType; 
	private DKafkaByteConsumer2 serverMessageConsumer; 
	private DKafkaByteProducer clientMessageProducer; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<GNetServerMessage> messageQueue = new LinkedBlockingQueue<GNetServerMessage>();
	
	
	@Override
	public void connect(StreamClientConnectorType config, String clientIdent, String stream) throws StreamClientException {
		myType = (StreamClientKafkaConnectorType)config;
		KafkaStreamClientRequest req = new KafkaStreamClientRequest();
		req.setClientIdentifier(clientIdent);
		req.setStream(stream);
		KafkaStreamClientResponse resp = null;
		String respString = null;
		try {
			 respString = DHttpHelper.postJson(myType.getRequestURL(), req);
		} catch (Exception e) {
			throw new StreamClientException("Exception invoking kafka request URL " + myType.getRequestURL() + " " + e.toString());
		}
		try {
			resp = DJson.getObjectMapper().readValue(respString, KafkaStreamClientResponse.class);
		} catch (Exception e) {
			throw new StreamClientException("Exception parsing response string to kafka client response " + e.toString());
		}
		if(resp.isError()) { 
			throw new StreamClientException("Server Response Exception " + resp.getException());
		}
		// else we are all good
		try {
			clientMessageProducer = DKafkaByteProducer.newInstance(resp.getKafkaBrokers(), resp.getServerMessageTopic(), clientIdent + "_" + DUUID.randomUUID(5));
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(resp.getKafkaBrokers())
					.addTopic(resp.getClientMessageTopic()).setClientAndGroup("StreamContainerClientMessageConsumer" + DUUID.randomUUID(4),
							"StreamContainerClientMessageConsumer" + DUUID.randomUUID(4))
					.build();
			serverMessageConsumer = DKafkaByteConsumer2.newInstance(spec);
			serverMessageConsumer.start();
			serverMessageConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new StreamClientException("Exception creating kafka producer/consumer " + e.toString());
		}
		
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GNetServerMessage message = GNetServerMessage.parseFrom(record.value());
			messageQueue.add(message);
		} catch (Exception e) {
			logger.error("Exception parsing gnet server message from byte record " + e.toString());
		}
	}



	@Override
	public void sendMessage(GNetClientMessage message) throws StreamClientException {
		try {
			clientMessageProducer.sendBytes(message.toByteArray());
		} catch (Exception e) {
			throw new StreamClientException("Exception sending kafka message " + e.toString());
		}
	}

	@Override
	public BlockingQueue<GNetServerMessage> getServerMessageQueue() {
		return messageQueue;
	}

	@Override
	public boolean isConnected() {
		return true; 
	}

	@Override
	public GNetClientConnectResponse getConnectResponse() throws StreamClientException {
		return GNetClientConnectResponse.newBuilder().setConnected(true).build();
	}

	
}
