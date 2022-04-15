package com.dunkware.common.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.InterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.DKafkaException;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.uuid.DUUID;

public class DKafkaByteConsumer {

	private static AtomicInteger instanceCounter = new AtomicInteger(0);
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<DKafkaByteHandler> handlers = new ArrayList<DKafkaByteHandler>();
	private Semaphore handlerLock = new Semaphore(1);
	private KafkaConsumer<String, byte[]> consumer;
	private AtomicInteger recordCount = new AtomicInteger(0);
	private ConsumerThread consumerThread;
	private boolean disposed = true;
	private DProperties properties = null;
	private Properties regProperties = null;
	private String connectionError = null;
	private AtomicBoolean inerrupted = new AtomicBoolean(true);
	private DKafkaByteConsumerStatus status = DKafkaByteConsumerStatus.Disconnected;
	private int instanceCount = 0;
			
	public static DKafkaByteConsumer newInstance(DKafkaConsumerSpec spec) throws DKafkaException { 
		if(spec.getBrokers() == null || spec.getTopics() == null) { 
			throw new DKafkaException("Consumer Spec must have brokers and topics defined");
		}
		
		Properties props = new Properties();
		props.put(DKafkaProperties.BOOTSTRAP_SERVERS, spec.getBrokers());
		props.put(DKafkaProperties.TOPICS, spec.getTopics());
		if(spec.getConsumerId() != null) 
			props.setProperty(DKafkaProperties.CLIENT_ID_CONFIG, spec.getConsumerId());
		if(spec.getConsumerGroup() != null) 
			props.setProperty(DKafkaProperties.GROUP_ID_CONFIG, spec.getConsumerGroup());
		if(spec.isSpecificOffset()) { 
			throw new DKafkaException("Must implement specific offset property ");
		}
		// assume its the latest
		return newInstance(props);
	}
	
	public static DKafkaByteConsumer newInstance(DProperties props) throws DKafkaException {
		DKafkaByteConsumer consumer = new DKafkaByteConsumer();
		consumer.connect(props);
		return consumer;
	}
	
	public static DKafkaByteConsumer newInstance(Properties props) throws DKafkaException { 
		DKafkaByteConsumer consumer = new DKafkaByteConsumer(); 
		consumer.connect(props);
		return consumer;
	}
	
	public static DKafkaByteConsumer newInstance(String brokers,String topics) throws DKafkaException { 
		DKafkaByteConsumer consumer = new DKafkaByteConsumer(); 
		Properties props = new Properties();
		props.put(DKafkaProperties.CLIENT_ID_CONFIG, DUUID.randomUUID(5) + 5);
		props.put(DKafkaProperties.GROUP_ID_CONFIG, DUUID.randomUUID(5) + 5);
		props.put(DKafkaProperties.BOOTSTRAP_SERVERS, brokers);
		props.put(DKafkaProperties.TOPICS, topics);
		consumer.connect(props);
		return consumer;
	}
	
	public static DKafkaByteConsumer newInstance(String brokers,String topics, int startingOffset) throws DKafkaException { 
		throw new DKafkaException("Implement new consumer with starting offset");
		
	}
	private void connect(Properties props) throws DKafkaException { 
		this.regProperties = props;
		instanceCount = instanceCounter.incrementAndGet();
		if (props.containsKey("topics") == false) {
			throw new DKafkaException("topic property not set on consumer ");
		}
		status = DKafkaByteConsumerStatus.Connecting;
		consumerThread = new ConsumerThread();
		consumerThread.start();
		disposed = false;
	}

	private void connect(DProperties properties) throws DKafkaException {
		instanceCount = instanceCounter.incrementAndGet();
		this.properties = properties;

		if (properties.hasProperty("topics") == false) {
			throw new DKafkaException("topic property not set on consumer ");
		}
		status = DKafkaByteConsumerStatus.Connecting;
		consumerThread = new ConsumerThread();
		consumerThread.start();
		disposed = false;

	}

	public String getConnectionError() {
		return connectionError;
	}

	public DKafkaByteConsumerStatus getStatus() {
		return status;
	}

	public void addStreamHandler(DKafkaByteHandler handler) {
		try {
			handlerLock.acquire();
			handlers.add(handler);
		} catch (Exception e) {

		} finally {
			handlerLock.release();
		}
	}

	private void notifyHandlers(byte[] message) {
		try {
			handlerLock.acquire();
			for (DKafkaByteHandler handler : handlers) {
				handler.streamBytes(message);
			}
		} catch (Exception e) {

		} finally {
			handlerLock.release();
		}
	}

	public void dispose() {
		if (status == DKafkaByteConsumerStatus.Connected) {
			inerrupted.set(true);
			consumerThread.dispose(); 
			status = DKafkaByteConsumerStatus.Disconnected;
		}

	}

	public int getRecordCount() {
		return recordCount.get();
	}

	private class ConsumerThread extends Thread {
		private volatile boolean dispose = false;
		
		public void dispose() {
			if(logger.isDebugEnabled()) {
				logger.debug("disposed() in voked on consumer thread settinf dispose to true");
			}
			dispose = true;
		}
		
		public void run() {
			setName("DKafka Consumer Thread Instance " + instanceCount);
			Properties jprops = null; 
			
			if(properties != null) { 
				jprops = properties.toJavaProperties();
			} else { 
				jprops = regProperties;
			}
			DKafkaProperties.addConsumerProperties(jprops);
			try {
				consumer = new KafkaConsumer<String, byte[]>(jprops);
			} catch (KafkaException e) {
				logger.error("Kafka Connect Exception " + e.toString(), e);
				status = DKafkaByteConsumerStatus.Exception;
				connectionError = e.toString();
				return;
			}

			try {
				String[] topics = null;
				if(properties != null) { 
					 topics = properties.getString("topics").split(",");
				} else { 
					topics = regProperties.get("topics").toString().split(",");
				}
				
				consumer.subscribe(Arrays.asList(topics));
			} catch (Exception e) {
				logger.error("Kafka Connect Subscribe Exception " + e.toString(), e);
				status = DKafkaByteConsumerStatus.Exception;
				connectionError = "Topic Subscribe Exception " + e.toString();
				return;
			}
			status = DKafkaByteConsumerStatus.Connected;
			while (!dispose) {
				try {
					ConsumerRecords<String, byte[]> records = consumer.poll(3000);
					for (ConsumerRecord<String, byte[]> record : records) {
						recordCount.incrementAndGet();
						byte[] bytes = record.value();
						//consumer.commitAsync();
						notifyHandlers(bytes);
						if(recordCount.get() > 10000) { 
							
							recordCount.set(0);
						}
					}
				} catch (InterruptException e) {
					if (!inerrupted.get()) {
						logger.error("Consumer Thread Exception Not Interrupted Flag Instance  " + instanceCount + " " + e.toString());
					}
					try {
						
					} catch (Exception e2) {
						logger.error("Kafka consumer.close() Close Exception !!! GET RID OF ME!! " + e2.toString());
					}

					if(logger.isDebugEnabled()) { 
						logger.debug("Setting Disconnected to true " + instanceCount);
					}
					status = DKafkaByteConsumerStatus.Disconnected;
					

					return;

				}

			}
			try {
				
				if (logger.isDebugEnabled()) {
					logger.debug("Closing Kafka Consumer {}",instanceCount);
				}
				
				consumer.close();
				if (logger.isDebugEnabled()) {
					logger.debug("Closed Kafka Consumer {}",instanceCount);
				}

			} catch (Exception e) {
				logger.error("Exception Closing kafka consumer in our own fuckin thread " + e.toString());
			}
			
		}
	}
}
