package com.dunkware.trade.tick.service.server.feed;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.admin.DKafkaAdmin;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.tick.TickHandler;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.model.feed.TickFeedSpec;

public class FeedStream implements TickHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private TickFeed feed;

	private String id;

	private String brokers;

	private DDateTime lastPing = null;

	private AtomicInteger pingCount = new AtomicInteger(0);

	private Integer[] tickTypes;

	private DDateTime lastTickTime;

	private DDateTime createdTime;

	private DKafkaByteProducer kafkaProducer;

	private DProperties consumerProperties;

	private PingMonitor monitor;

	private FeedService tickService;

	private String kafkaTopic;

	private boolean monitorInterrupted = false;

	private AtomicLong tickCount = new AtomicLong(0);

	private TickFeedSpec feedSpec;
	public FeedStream() {

	}

	public void start(TickFeedSpec feedSpec, FeedService tickService)
			throws Exception {
		this.tickService = tickService;
		this.feedSpec = feedSpec;
		if (tickService.getProviders().size() == 0) {
			throw new Exception("No Tick Providers Found");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formatDateTime = LocalDateTime.now().format(formatter);
		String timestamp = formatDateTime;
		String random = DUUID.randomUUID(4);
		this.id = feedSpec.getName() + "_" + timestamp + "_" + random;
		this.kafkaTopic = "stree_feed" + feedSpec.getName() + "_" + timestamp + "_" + random;
		if (logger.isDebugEnabled()) {
			logger.debug("Tick Feed {} Starting ID {} Topic {}", feedSpec.getName(), id,kafkaTopic);
		}
		this.brokers = tickService.getKafkaBrokers();
		DKafkaAdmin admin = DKafkaAdmin.newInstance(tickService.getKafkaBrokers());
		try {
			admin.createTopic(kafkaTopic, 1, (short) 2, null);
			admin.close();
		} catch (Exception e) {
			logger.error("Exception Creating Tick Feed Kafka Topic " + e.toString(), e);
			;
			throw new Exception("Exception Creating Kafka Tick Feed Topic  " + e.toString());
		}
		// create kafka producer
		kafkaProducer = DKafkaByteProducer.newInstance(tickService.getKafkaBrokers(), kafkaTopic, kafkaTopic);

		try {
			// create Trick Provider Trade Stream
			FeedServiceProvider serviceProvider = tickService.getProviders().get(0);
			this.feed = serviceProvider.getProvider().createFeed(this.feedSpec);
			this.feed.addTickHandler(this);
			
		} catch (Exception e) {
			logger.error("Tick Feed {} Start Exception {}",id,e.toString());
			throw new Exception("Exception creating trade stream from provider " + e.toString());
		}
		// create consumer properties
		DPropertiesBuilder pbuilder = DPropertiesBuilder.newBuilder();
		pbuilder.addProperty(DKafkaProperties.TOPICS, kafkaTopic);
		pbuilder.addProperty(DKafkaProperties.IDENTIFIER, kafkaTopic);
		pbuilder.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, tickService.getKafkaBrokers());
		this.consumerProperties = pbuilder.build();
		lastPing = DDateTime.now();
		monitor = new PingMonitor();
		monitor.start();
	}

	public DProperties getConsumerProperties() {
		return consumerProperties;
	}

	public String getName() { 
		return feedSpec.getName(); 
	}
	
	public String getKafkaBrokers() { 
		return brokers;
	}
	
	public String getKafkaTopic() { 
		return kafkaTopic;
	}
	
	public String getId() {
		return id;
	}

	public void update(TickFeedSpec sub) throws Exception {
		this.feed.update(sub);
	}

	public void dispose(boolean timeout) {
		if (timeout) {
			logger.debug("Tick Feed Timeout {} ", id);
		}
		logger.debug("Disposing Tick Feed ID {} Kafka Topic {} Timeout {} " + kafkaTopic);

		this.feed.removeTickHandler(this);
		kafkaProducer.dispose();
		try {
			DKafkaAdmin admin = DKafkaAdmin.newInstance(tickService.getKafkaBrokers());
			Thread.sleep(2000);
			admin.deleteTopics(kafkaTopic);
			admin.close();
		} catch (Exception e) {
			logger.error("Exception deleting stream topic " + e.toString());
		}
		try {
			feed.dispose();
			if (!monitorInterrupted) {
				monitor.interrupt();
			}

			tickService.removeStream(this);
		} catch (Exception e) {
			logger.error("exception disposing shit " + e.toString());
		}

	}

	public void ping() {
		lastPing = DDateTime.now();
		pingCount.incrementAndGet();
	}

	@Override
	public void onTick(Tick tick) {
		try {
			tickCount.incrementAndGet();
			// here we want to somehow inject the fuckin id of entity
			kafkaProducer.sendBytes(tick.toByteArray());
		} catch (Exception e) {
			logger.error("Tick Feed {} Exception Producing Tick {}", id, e.toString());
		}

	}

	private class PingMonitor extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					Thread.sleep(1000);
					DDateTime now = DDateTime.now();
					long diff = ChronoUnit.MINUTES.between(lastPing.get(),now.get());
					if (diff > 0) {
						dispose(true);
						return;
					}
				}
			} catch (Exception e) {
				logger.error("Tick Feed Exception Ping Monitor " + e.toString());
			} finally {
				monitorInterrupted = true;
			}
		}
	}

}
