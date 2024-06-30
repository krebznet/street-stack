package com.dunkware.utils.kafka.byteproducer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaByteProducer {

	public static KafkaByteProducer newInstance(String brokers, String topics, String identifier) throws Exception {
		Properties props = new Properties();
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokers);
		props.put("acks", "0");
		props.put("retries", 2);
		props.put("batch.size", 2500);
		props.put("linger.ms", 2);
		props.put("buffer.memory", 335544323);
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "5000");
		props.put("client.dns.lookup", "use_all_dns_ips");
		props.put("topics", topics);
		props.put("identifier", identifier);
		

		KafkaByteProducer prod = new KafkaByteProducer();
		prod.connect(props);
		return prod;
	}

	public Producer<Integer, byte[]> getProducer() {
		return producer;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String identifier;
	private AtomicInteger sendCount = new AtomicInteger(0);
	private AtomicInteger callbackCount = new AtomicInteger(0);

	private Producer<Integer, byte[]> producer;
	private ProducerCallback producerCallback = new ProducerCallback();
	private Properties props;
	private boolean disposed = true;
	private String[] topics;

	private LogThread logThread;

	public void connect(Properties props) throws Exception {
		this.topics = props.get("topics").toString().split(",");
		this.props = props;
		producer = new KafkaProducer<>(props);
		disposed = false;
		logThread = new LogThread();
		this.logThread.start();
	}

	public void sendBytes(byte[] bytes) throws Exception {
		if (disposed) {
			throw new Exception("Producer is disposed cannot send bytes");
		}
		for (String topic : topics) {
			ProducerRecord<Integer, byte[]> record = new ProducerRecord<Integer, byte[]>(topic,
					sendCount.incrementAndGet(), bytes);
			producer.send(record, producerCallback);
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
			if (arg1 != null) {
				logger.error("Exception send ident {} callback {} ", identifier, arg1.toString(), arg1);
			}
		}
	}

	public String getIdentifier() {
		return identifier;
	}

	private class LogThread extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(5000);
					logger.trace("{}:{}:{}", identifier, sendCount.get(), callbackCount.get());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
