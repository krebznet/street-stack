package com.dunkware.common.kafka.producer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.DKafkaException;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.spec.kafka.DKafkaProducerSpec;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;
import com.dunkware.common.util.uuid.DUUID;

public class DKafkaByteProducer {
	
	public static DKafkaByteProducer newInstance(DKafkaProducerSpec spec) throws DKafkaException { 
		if(spec.getBrokers() == null || spec.getTopics() == null) { 
			throw new DKafkaException("Spec must have topics and brokers set");
		}
		if(spec.getIdentifier() == null) { 
			spec.setIdentifier(DUUID.randomUUID(5));
		}
		return newInstance(spec.getBrokers(),spec.getTopics(),spec.getIdentifier());
	}
	
	public static DKafkaByteProducer newInstance(String brokers, String topics, String identifier) throws DKafkaException { 
		DProperties props = DPropertiesBuilder.newBuilder().addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, brokers)
		.addProperty(DKafkaProperties.TOPICS, topics)
		.addProperty(DKafkaProperties.IDENTIFIER, identifier).build();
		DKafkaByteProducer prod = new DKafkaByteProducer();
		prod.connect(props);
		return prod;
	}
	
	public static DKafkaByteProducer newInstance(DProperties props) throws DKafkaException { 
		DKafkaByteProducer prod = new DKafkaByteProducer();
		prod.connect(props);
		return prod;
	}

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DProperties props;
	private String identifier;
	private AtomicInteger sendCount = new AtomicInteger(0);
	private AtomicInteger callbackCount = new AtomicInteger(0);
	
	private Producer<String, byte[]> producer;
	private ProducerCallback producerCallback = new ProducerCallback();
	
	private boolean disposed = true;
	private String[] topics;

	private LogThread logThread; 
	public void connect(DProperties props) throws DKafkaException {
		try {
			props.validate(new DKafkaByteProducerValidator());	
		} catch (Exception e) {
			throw new DKafkaException("Producer Properties validation exception " + e.toString());
		}
		
		this.props = props;
		if(props.hasProperty(DKafkaProperties.IDENTIFIER) == false) { 
			this.identifier = DUUID.randomUUID(5);
		} else { 
			this.identifier = props.getString(DKafkaProperties.IDENTIFIER);	
		}
		this.topics = props.getString(DKafkaProperties.TOPICS).split(",");
		Properties jProps = props.toJavaProperties();
		DKafkaProperties.addProducerProperties(jProps);
		producer = new KafkaProducer<>(jProps);
		disposed = false;
		logThread = new LogThread();
		this.logThread.start();
	}

	public void sendBytes(byte[] bytes) throws DKafkaException {
		if(disposed) { 
			throw new DKafkaException("Producer is disposed cannot send bytes");
		}
		for (String topic : topics) {
			ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>(topic,
					String.valueOf(sendCount.incrementAndGet()), bytes);	
			producer.send(record,producerCallback);
		}
	}

	public void dispose() {
		producer.close();
		logThread.interrupt();
		disposed = true;
	}

	private class ProducerCallback implements Callback {

		@Override
		public void onCompletion(RecordMetadata arg0, Exception arg1) {
			if (arg1 != null)
				callbackCount.incrementAndGet();
		}

	}
	
	public String getIdentifier() { 
		return identifier;
	}
	
	private class LogThread extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
					logger.trace("{}:{}:{}",identifier,sendCount.get(),callbackCount.get());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
