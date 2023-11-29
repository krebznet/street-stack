package com.dunkware.trade.tick.api.consumer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.common.config.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.admin.DKafkaAdminClient;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopic;
import com.dunkware.common.kafka.admin.model.DKafkaNewTopicResult;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.tick.api.feed.TickFeed;
import com.dunkware.trade.tick.api.feed.TickFeedSubscription;
import com.dunkware.trade.tick.model.TradeTicks;
import com.dunkware.trade.tick.model.consumer.TickConsumerSession;
import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class TickConsumer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private TickConsumerSpec spec;
	private TickFeed feed;

	private DKafkaByteProducer kafkaProducer;
	private List<TickFeedSubscription> consumerSubscriptions = new ArrayList<TickFeedSubscription>();

	private String kafkaBrokers;
	private String topicPrefix;
	private TickConsumerSession session;

	private BlockingQueue<Tick> tickQueue = new LinkedBlockingQueue<Tick>();

	private TickSender tickSender;
	private TickProducer tickProducer;

	private LocalDateTime lastHeartbeat = null;
	
	private TickConsumerBean bean = new TickConsumerBean();

	public TickConsumerSession start(TickConsumerSpec spec, TickFeed feed, String kafkaBrokers, String topicPrefix)
			throws Exception {
		session = new TickConsumerSession();
		bean.setName(spec.getName());
		
		this.spec = spec;
		this.kafkaBrokers = kafkaBrokers;
		
		for (TradeTickerSpec ticker : spec.getTickers()) {
			if (feed.hasSubscription(ticker.getSymbol())) {

				consumerSubscriptions.add(feed.getSubscription(ticker.getSymbol()));
				session.setActiveSubscriptions(session.getActiveSubscriptions() + 1);
			} else {
				session.setInactiveSubscriptions(session.getInactiveSubscriptions() + 1);
			}
		}
		

		String sessionid = ("tick_feed_" + spec.getName() + "_" + DUUID.randomUUID(4));
		session.setSessionId(sessionid);
		session.setKafkaBroker(kafkaBrokers);
		session.setKafkaTopic(topicPrefix + "_" + sessionid);

		try {
			kafkaProducer = DKafkaByteProducer.newInstance(kafkaBrokers, session.getKafkaTopic(), DUUID.randomUUID(5));
			DKafkaAdminClient admin = DKafkaAdminClient.newInstance(kafkaBrokers);
		DKafkaNewTopic newTopic =	DKafkaNewTopic.builder().name(session.getKafkaTopic()).paritions(1).replicas((short)1).cleanupPolicy(TopicConfig.CLEANUP_POLICY_DELETE).retentionTime(3, TimeUnit.DAYS).paritions(1).build();
			
			DKafkaNewTopicResult result = admin.createTopic(newTopic);
			if(result.isException()) { 
				logger.error("Exception creating tick feed client with new admin " + result.getCause());	
				throw new Exception("new admin client not creting opic " + result.getCause());
			}
			if(result.isTimeout()) { 
				logger.error("timeout creting new feed topic" );
				throw new Exception("feed topic creation timeot");
			}
			admin.close();
		} catch (Exception e) {
			throw new Exception("Exception connecting to kafka for sending ticks " + e.toString());
		}
		try {
			tickProducer = new TickProducer();
			tickSender = new TickSender();
			tickProducer.start();
			tickSender.start();
		} catch (Exception e) {
			logger.error("Exception starting tick senders and producers " + e.toString());
			throw e;
		}

		return session;
	}

	public void dispose() {
		tickProducer.interrupt();
		tickSender.interrupt();
		kafkaProducer.getClass();
		try {
			//DKafkaAdminClient admin = DKafkaAdminClient.newInstance(kafkaBrokers);
			//admin.d(session.getKafkaTopic());
			//admin.close();
		} catch (Exception e) {
			logger.error("Exception deleting tick consumer topic " + e.toString());
		}

	}

	public void clientHeartbeat() {
		lastHeartbeat = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
	}

	private class TickSender extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Tick tick = tickQueue.take();
					kafkaProducer.sendBytes(tick.toByteArray());
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Sending Tick in Sender " + e.toString());
					continue;
					// log error
				}
			}
		}
	}

	private class TickProducer extends Thread {

		public void run() {
			while (!interrupted()) {
				for (TickFeedSubscription sub : consumerSubscriptions) {
					List<TickField> fields = new ArrayList<TickField>();
					int identifier = sub.getIdentifier();
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldSymbolId).setIntValue(identifier)
							.setType(TickFieldType.INT).build());
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldSymbol).setStringValue(sub.getSymbol())
							.setType(TickFieldType.STRING).build());
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldVolume)
							.setLongValue(sub.getVolume()).setType(TickFieldType.LONG).build());
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldTradeCount).setIntValue(sub.getTrades())
							.setType(TickFieldType.INT).build());
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldAskPrice).setDoubleValue(sub.getAskPrice())
							.setType(TickFieldType.DOUBLE).build());
			
					fields.add(TickField.newBuilder().setId(TradeTicks.FieldLastPrice)
							.setDoubleValue(sub.getLastPrice()).setType(TickFieldType.DOUBLE).build());

					Tick tick = Tick.newBuilder().setType(TradeTicks.TickSnapshot).addAllTickFields(fields).build();
					
					tickQueue.add(tick);
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Tick Producer Exception " + e.toString());
					continue;
				}

			}
		}
	}

}
