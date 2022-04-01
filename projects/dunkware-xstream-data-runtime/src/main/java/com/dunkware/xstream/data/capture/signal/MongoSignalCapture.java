package com.dunkware.xstream.data.capture.signal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.data.capture.MongoCaptureHelper;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class MongoSignalCapture implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicInteger signalConsumeCount = new AtomicInteger();
	private AtomicInteger signalWriteCount = new AtomicInteger();
	private AtomicInteger errorLogCount = new AtomicInteger();
	private AtomicInteger pauseCount = new AtomicInteger(0);

	private DKafkaByteConsumer2 kafkaConsumer;

	private BlockingQueue<GEntitySignal> signalQueue = new LinkedBlockingQueue<GEntitySignal>();
	private MongoSignalCaptureInput input;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private double lastWriteTime = 0;

	private boolean running = false;

	private ZoneId timeZone;

	private SignalWriter signalWriter;
	private QueueMonitor queueMonitor;
	private DebugLogger debugLogger;

	private volatile GEntitySignal lastSignal = null;
	private volatile int lastBatchInsertSize = 0;
	private double lastBulkInsertTime = 0;

	public MongoSignalCapture() {

	}

	public void start(MongoSignalCaptureInput input) throws Exception {
		this.input = input;
		timeZone = DTimeZone.toZoneId(input.getTimeZone());
		try {
			mongoClient = MongoClients.create(input.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(input.getMongoDatabase());
			signalCollection = mongoDatabase.getCollection(input.getMongoCollection()).withWriteConcern(wc);

		} catch (Exception e) {
			logger.error("Exceptoon Init Mongo DB " + e.toString());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}

		try {
			kafkaConsumer = DKafkaByteConsumer2.newInstance(input.getKafkaSpec());
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			logger.error("Exception connecting to kafka consumer " + e.toString(), e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}

		queueMonitor = new QueueMonitor();
		queueMonitor.start();
		signalWriter = new SignalWriter();
		signalWriter.start();
		if (input.isDebugLogging()) {
			debugLogger = new DebugLogger();
			debugLogger.start();
		}
	}

	public MongoSignalCaptureStats getStats() {
		MongoSignalCaptureStats stats = new MongoSignalCaptureStats();
		stats.setErrorCount(errorLogCount.get());
		stats.setLastBulkWriteTime(lastWriteTime);
		stats.setQueueSize(signalQueue.size());
		stats.setConsumeCount(signalConsumeCount.get());
		stats.setPauseCount(pauseCount.get());
		stats.setWriteCount(signalWriteCount.get());;
		if (lastSignal != null) {
			LocalDateTime lastTime = DProtoHelper.toLocalDateTime(lastSignal.getTime(), input.getTimeZone());
			String time = DunkTime.format(lastTime, DunkTime.YYYY_MM_DD_HH_MM_SS);
			stats.setLastSignalTime(time);
		} else {
			stats.setLastSignalTime("na");
		}
		return stats;
	}
	
	public void dispose() { 
		kafkaConsumer.dispose();
		signalWriter.interrupt();
		if(debugLogger != null) { 
			debugLogger.interrupt();
		}
		queueMonitor.interrupt();
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {

		try {
			GStreamEvent event = null;
			event = GStreamEvent.parseFrom(record.value());

			if (event.getType() == GStreamEventType.EntitySignal) {
				GEntitySignal signal = event.getEntitySignal();
				try {
					signalQueue.put(signal);
					signalConsumeCount.incrementAndGet();
				} catch (Exception e) {
					logger.error("Exception putting signal in q " + e.toString());
				}

			} else {
				return;
			}
		} catch (Exception e) {
			logger.error("Exception parsing signal from bytes " + e.toString());
			errorLogCount.incrementAndGet();
			return;
		}

	}

	private class DebugLogger extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(10000);
					MongoSignalCaptureStats stats = getStats();
					logger.debug("Signal Capture Queue Size {} Write Count {} Last Signal Time {} Error Count {}",
							stats.getQueueSize(), stats.getWriteCount(), stats.getLastSignalTime(),
							stats.getErrorCount());
					System.out.println("Signals Consumed " + stats.getConsumeCount() + " Signals Write Count " + stats.getWriteCount());
				} catch (Exception e) {
					return;
				}

			}
		}

	}

	private class QueueMonitor extends Thread {

		volatile boolean paused = false;

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(500);
					if (paused) {
						if (signalQueue.size() < input.getSignalQueueSizeLimit()) {
							System.out.println("resuming consumer");
							kafkaConsumer.resumeConsumer();
							paused = false;
						}
						continue;

					}
					if (signalQueue.size() > input.getSignalQueueSizeLimit()) {
						System.out.println("pausing consumer");
						kafkaConsumer.pauseConsumer();
						pauseCount.incrementAndGet();
						paused = true;
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception monitoring queue size " + e.toString(), e);
					errorLogCount.incrementAndGet();
				}

			}
		}
	}

	private class SignalWriter extends Thread {

		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();

		public void run() {
			DStopWatch watch = DStopWatch.create();
			while (!interrupted()) {
				// every 3 seconds remove signals from queue and write
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Excepetion sleeping in signal writer " + e.toString());
				}

				if (signalQueue.size() > 0) {
					List<GEntitySignal> signals = new ArrayList<GEntitySignal>();
					signalQueue.drainTo(signals,25000);
					for (GEntitySignal signal : signals) {
						try {
							lastSignal = signal;
							Document doc = MongoCaptureHelper.buildEntitySignal(signal, input.getTimeZone());
							pendingWrites.add(new InsertOneModel<Document>(doc));
						} catch (Exception e) {
							logger.error("Exception building signal document " + e.toString());
							errorLogCount.incrementAndGet();
						}
					}
					lastBatchInsertSize = pendingWrites.size();
					try {
						watch.start();
						signalCollection.bulkWrite(pendingWrites);
						watch.stop();
						signalWriteCount.addAndGet(pendingWrites.size());
						lastBulkInsertTime = watch.getCompletedSeconds();
						pendingWrites.clear();
					} catch (Exception e) {
						logger.error("Exception writing signal batch " + e.toString());
						errorLogCount.incrementAndGet();
					}

				}
			}

		}
	}

}
