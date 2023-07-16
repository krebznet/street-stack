package com.dunkware.common.kafka.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.InterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.DKafkaException;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ThrottleType;
import com.dunkware.common.util.helpers.DStringHelper;
import com.dunkware.common.util.properties.DProperties;

public class DKafkaByteConsumer2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<DKafkaByteHandler2> handlers = new ArrayList<DKafkaByteHandler2>();
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

	private DKafkaByteConsumer2Spec spec = null;

	private List<TopicPartition> topicPartitions = new ArrayList<TopicPartition>();
	private List<PartitionInfo> partitionInfos = new ArrayList<PartitionInfo>();

	private boolean throttle = false;
	private int throttleLimit = 9;

	private AtomicBoolean paused = new AtomicBoolean(false);

	private BlockingQueue<ConsumerRecord<String, byte[]>> recordQueue = new LinkedBlockingQueue<ConsumerRecord<String, byte[]>>();

	private ThrottleThread throttleThread;
	private HandlerThread handlerThread;

	public static DKafkaByteConsumer2 newInstance(DKafkaByteConsumer2Spec spec) {
		return new DKafkaByteConsumer2(spec);
	}

	private DKafkaByteConsumer2(DKafkaByteConsumer2Spec spec) {
		this.spec = spec;

	}

	public void start() throws DKafkaException {

		// init throttling
		if (spec.getThrottleType() != null) {
			if (spec.getThrottleType() == ThrottleType.Manual) {
				throttle = true;
				throttleLimit = spec.getThrottleLimit();
			}
		}

		String brokers = DStringHelper.convertArrayToCSV(spec.getBrokers());
		String topics = DStringHelper.convertArrayToCSV(spec.getTopics());

		// Create Properties For Kafka Consumer
		Properties props = new Properties();
		props.put("bootstrap.servers", brokers);
		props.put("topics", topics);
		props.put("client.id", spec.getConsumerId());
		props.put("group.id", spec.getConsumerGroup());
		props.put("batch.size", 5000);
		if (spec.getOffsetType() == OffsetType.Earliest)
			props.put("auto.offset.reset", "earliest");
		if (spec.getOffsetType() == OffsetType.Latest)
			props.put("auto.offset.reset", "latest");
		// manual we don't set this?
		props.put("heartbeat.interval.ms", "2500");
		props.put("max.poll.records", 1000);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "2500");
		//props.put("session.timeout.ms", "3000");
		props.put("buffer.memory", 835544323);
		props.put("fetch.max.wait.ms", 30);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		status = DKafkaByteConsumerStatus.Connecting;
		// try to connect to the consumer
		try {
			consumer = new KafkaConsumer<String, byte[]>(props);
		} catch (KafkaException e) {
			throw new DKafkaException("Excepton creating kafka consumer " + e.toString());
		}

		// build the partition info list from all topics we are consuming
		for (String top : spec.getTopics()) {
			List<PartitionInfo> parts = consumer.partitionsFor(top);
			for (PartitionInfo partitionInfo : parts) {
				this.partitionInfos.add(partitionInfo);
			}
		}
		int sleepCount = 0;

		if (spec.getConsumerType() == ConsumerType.Auto) {
			consumer.subscribe(Arrays.asList(spec.getTopics()));
			// add our dynamically assigned partitions

			/// just create topic partitions from partioninfos
			for (PartitionInfo info : partitionInfos) {
				TopicPartition p = new TopicPartition(info.topic(), info.partition());
				this.topicPartitions.add(p);
			}

		}

		if (spec.getConsumerType() == ConsumerType.AllPartitions) {
			for (PartitionInfo info : partitionInfos) {
				TopicPartition part = new TopicPartition(info.topic(), info.partition());
				topicPartitions.add(part);
			}
			consumer.assign(topicPartitions);
		}

		if (spec.getConsumerType() == ConsumerType.Manual) {
			// assuming single topic here validated in spec builder
			Integer[] partitionIds = spec.getPartitions();
			String topic = spec.getTopics()[0];
			int index = 0;
			for (Integer partition : partitionIds) {
				this.topicPartitions.add(new TopicPartition(topic, partition));
				// todo would be nice to see that its a valid partition
				index++;
			}
			consumer.assign(topicPartitions);
		}

		if (spec.getOffsetType() == OffsetType.Manual) {
			// no we need to figure out how to
			// specify the offset is it seekTo?
			// we assume offset type is manual then consumer type must be manual
			Integer[] partitionIds = spec.getPartitions();
			String topic = spec.getTopics()[0];
			List<PartitionInfo> manualPartitionInfos = new ArrayList<PartitionInfo>();
			for (PartitionInfo partitionInfo : partitionInfos) {
				if (partitionInfo.topic().equals(topic)) {
					manualPartitionInfos.add(partitionInfo);
				}

			}
			int index = 0;
			for (Integer partitionSpec : spec.getPartitions()) {
				for (PartitionInfo partitionInfo : manualPartitionInfos) {
					if (partitionInfo.partition() == partitionSpec) {
						Long offset = spec.getPartitionOffsets()[index];
						consumer.seek(new TopicPartition(topic, partitionSpec), offset);
						continue;
					}
					throw new DKafkaException("Parition " + partitionSpec + " not found on topic " + topic
							+ " when setting manual offset");
				}
			}
		}

		status = DKafkaByteConsumerStatus.Connecting;
		consumerThread = new ConsumerThread();
		consumerThread.start();

		if (spec.getThrottleType() == ThrottleType.Manual) {
			throttleThread = new ThrottleThread();
			throttleThread.start();
		}

		handlerThread = new HandlerThread();
		handlerThread.start();

	}

	public DKafkaByteConsumerStatus getStatus() {
		return status;
	}

	public void addStreamHandler(DKafkaByteHandler2 handler) {
		try {
			handlerLock.acquire();
			handlers.add(handler);
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

	public void pauseConsumer() throws DKafkaException {
		consumerThread.pause();
	}

	public void resumeConsumer() throws DKafkaException {
		consumerThread.unpause();
	}

	private class ThrottleThread extends Thread {

		private volatile boolean sleeping = false;

		public void run() {
			setName("DKafkaConsumerThrottle:" + spec.getConsumerId());
			while (!interrupted()) {
				try {
					Thread.sleep(500);
					if (sleeping) {
						if (DKafkaByteConsumer2.this.recordQueue.size() < throttleLimit) {
							resumeConsumer();
							sleeping = false;
						}
					} else {
						if (recordQueue.size() > throttleLimit) {
							if (logger.isDebugEnabled()) {
								logger.debug("Pausing DKafkaConsumer " + spec.getConsumerId());
							}
							pauseConsumer();
							sleeping = true;

						}
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception in throttle thread " + e.toString(), e);

				}
			}
		}
	}

	private class HandlerThread extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					ConsumerRecord<String, byte[]> record = recordQueue.take();
					try {

						handlerLock.acquire();
						for (DKafkaByteHandler2 handler : handlers) {
							handler.record(record);
						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error("DKafkaConsumer2 Handler Thread Exception " + e.toString());
					} finally {
						handlerLock.release();
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("DKafkaConsumer2 Handler Thread Exception " + e.toString());
				}
			}
		}
	}

	private class ConsumerThread extends Thread {
		private volatile boolean dispose = false;
		private volatile boolean paused = false;
		private volatile boolean consumerPaused = false;
		private boolean printPoolCount = false;

		public void dispose() {
			if (logger.isDebugEnabled()) {
				logger.debug("disposed() in voked on consumer thread settinf dispose to true");
			}
			dispose = true;
		}

		public void pause() {
			paused = true;
		}

		public void unpause() {
			paused = false;
		}

		public void run() {
			setName("DKafkaConsumer2:" + spec.getConsumerId());
			while (!dispose) {
				try {
					if (paused && consumerPaused == false) {
						consumer.pause(consumer.assignment());
						System.out.println("consumer paused");
						consumerPaused = true;
					}
					if (consumerPaused && paused == false) {
						consumer.resume(consumer.paused());
						System.out.println("consumer resumed");
						printPoolCount = true;
						consumerPaused = false;
					}

					ConsumerRecords<String, byte[]> records = consumer.poll(3000);
					if (printPoolCount) {
						System.out.println("Consumer consumed " + records.count());
						printPoolCount = false;
					}
					for (ConsumerRecord<String, byte[]> record : records) {
						recordCount.incrementAndGet();
						recordQueue.add(record);
					}
				} catch (InterruptException e) {
					if (!inerrupted.get()) {
						logger.error("Consumer Thread Exception Not Interrupted Flag Instance  " + instanceCount + " "
								+ e.toString());
					}
					try {

					} catch (Exception e2) {
						logger.error("Kafka consumer.close() Close Exception !!! GET RID OF ME!! " + e2.toString());
					}

					if (logger.isDebugEnabled()) {
						logger.debug("Setting Disconnected to true " + instanceCount);
					}

					return;
				}

			}

		}
	}
}
