package com.dunkware.trade.net.data.server.stream.capture.writers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.trade.net.data.server.stream.converters.MongoStreamConverter;
import com.dunkware.trade.net.data.server.stream.provider.StreamDataProvider;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class SignalWriter implements DKafkaByteHandler2 {
	

	private StreamDescriptor descriptor;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntitySnapshotConsumer");

	private DKafkaByteConsumer2 kafkaConsumer;

	private MongoCollection<Document> snapshotCollection;

	private ZoneId timeZone;

	private MongoWriter mongoWriter;
	private QueueMonitor queueMonitor;

	private int bucketBatchSize = 15000;
	
	private boolean terminated = false;

	private Map<Integer, AtomicInteger> entities = new ConcurrentHashMap<Integer, AtomicInteger>();

	private SnapshotWriterMetrics metrics;

	private BlockingQueue<StreamEntitySignal> writeQueue = new LinkedBlockingQueue<StreamEntitySignal>();

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection mongoCollection;
	
	private int queueSizeLimit = 50; 
	private boolean paused = false; 
	private boolean completed = false;
	private boolean writerClosed = false;
	private StreamDataProvider dataProvider; 

//	private IStreamDataController 
	public void start(StreamDataProvider dataProvider) throws Exception {
		logger.info("Starting Data Snapshot writer");
		this.dataProvider = dataProvider; 
		this.descriptor = dataProvider.getDescriptor();
		marker = MarkerFactory.getMarker("signal_capture_" + descriptor.getIdentifier());
		logger.info(marker, "Starting Signal Writer on stream {}", descriptor.getIdentifier());
		timeZone = DTimeZone.toZoneId(descriptor.getTimeZone());
		try {

			mongoClient = MongoClients.create(descriptor.getCoreDatabaseURL());

			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(descriptor.getCoreDatabase());

			// check if the database exists
			LocalDateTime dt = LocalDateTime.now(DTimeZone.toZoneId(descriptor.getTimeZone()));
			String mongoCollectionName = descriptor.getSignalMongoCollection();
			boolean exists = false;
			for (String colName : mongoDatabase.listCollectionNames()) {
				if (colName.equals(mongoCollectionName)) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				logger.error("Creating signal collection Collection " + mongoCollectionName);
				//TimeSeriesOptions tsOptions = new TimeSeriesOptions("time");
				//tsOptions.granularity(TimeSeriesGranularity.SECONDS);
				//tsOptions.metaField("vars");
				try {
					//CreateCollectionOptions colOptions = new CreateCollectionOptions().timeSeriesOptions(tsOptions);
				//	mongoDatabase.createCollection(mongoCollectionName, colOptions);
				} catch (Exception e) {
					logger.error("Collection Snapshot Creation Failed " + e.toString());
					terminated = true;
					return;
				}

			}
			snapshotCollection = mongoDatabase.getCollection(mongoCollectionName).withWriteConcern(wc);
			logger.info(MarkerFactory.getMarker("SnapshotWriter"),
					"Created mongo client to " + mongoDatabase + " " + mongoCollectionName);
			;

			logger.info(MarkerFactory.getMarker("SnapshotWriter"),
					"Created mongo client to " + mongoDatabase + " " + mongoCollectionName);
			;

		} catch (Exception e) {

			logger.error("Exception Connecting Signal writer " + e.toString());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		String snapshotTopic = descriptor.getSignalEventTopic();

		DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
				.setBrokerString(descriptor.getKafkaBrokers()).addTopic(snapshotTopic)
				// probbably should change this to keep the same id 
				.setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
		logger.info(marker, "Consuming signal topics from " + snapshotTopic);
		try {

			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			// idiot start the fucking consumer
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
			logger.info("started kafka consumer on topic " + spec.getTopics());
		} catch (Exception e) {

			logger.error(marker, "Exception connecting to kafka consumer " + e.toString(), e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}
		mongoWriter = new MongoWriter();
		mongoWriter.start();
		queueMonitor = new QueueMonitor();
		queueMonitor.start();
	}

	public StreamDescriptor getDescriptor() { 
		return  descriptor;
	}
	public int getQueueSize() {
		return writeQueue.size();
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		StreamEntitySignal snapshot = null;
		try {
			snapshot = DJson.getObjectMapper().readValue(record.value(), StreamEntitySignal.class);
			//.record..snapshotConsume(snapshot);
			writeQueue.put(snapshot);

		} catch (Exception e) {
			logger.error(marker, "Exception parsing snapshot from bytes " + e.toString());
			return;
		}

	}

	private class QueueMonitor extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(500);
					if (paused) {
						if (writeQueue.size() < queueSizeLimit) {
							metrics.consumerResumed();
							kafkaConsumer.resumeConsumer();
							paused = false;
						}
						continue;

					}
					if (writeQueue.size() > queueSizeLimit) {
						metrics.consumerPaused();
						kafkaConsumer.pauseConsumer();
						paused = true;
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception monitoring queue size " + e.toString(), e);
				}

			}
		}
	}

	public boolean isCompleted() {
		if (terminated) {
			return true;
		}
		return completed;
	}

	public void closeWriter() {
		logger.info(marker, "Closing Session Snapshot Writer");
		int count = 0;

		// while the consumer is paused because we are catching up
		// lets wait up to 30 seconds.
		while (paused) {
			try {
				Thread.sleep(250);
				count++;
			} catch (Exception e) {
				logger.error(marker, "Internal exception closing writer while paused " + e.toString());
			}
			// 30 seconds
			if (count > 120) {
				logger.error(marker, "Closing Writer waiting on consumer paus timed out after 30 seconds");
				break;
			}
		}

		// lets wait up to 30 seconds for the consumer record write queue to clear up.
		count = 0;
		while (writeQueue.size() > 0) {
			try {
				Thread.sleep(250);
				count++;
				if (count > 120) {
					logger.error(marker, "Closing writer snapshot queue size not empty after 30 seconds");
					break;
				}
			} catch (Exception e) {
				logger.error(marker, "Closing Writer waiting on write queue size = 0 exception " + e.toString());
			}
		}
		// lets write any pending snapshots
		mongoWriter.writePendingSnapshots();

		// lets now clean up resources.
		disposeWriter();

	}

	private void disposeWriter() {
		mongoClient.close();
		mongoWriter.interrupt();
		mongoWriter.writePendingSnapshots();
		queueMonitor.interrupt();
		metrics.setStopTime(DDateTime.now(descriptor.getTimeZone()));
		kafkaConsumer.dispose();
		writerClosed = true;
	}

	private class MongoWriter extends Thread {

		private DStopWatch sw = DStopWatch.create();
		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		private List<StreamEntitySignal> pendingWriteSnapshots = new ArrayList<StreamEntitySignal>();

		public void writePendingSnapshots() {

			if (pendingWrites.size() > 0) {
				if (logger.isDebugEnabled()) {
					logger.debug("Snapshot Writer inserting pending writes after interruption " + pendingWrites.size());
				}
				sw.start();
				snapshotCollection.bulkWrite(pendingWrites);
				sw.stop();
				if (logger.isDebugEnabled()) {
					logger.debug("Snapshot Write Size " + pendingWrites.size() + " time " + sw.getCompletedSeconds());
				}
				// metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
				//metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
				pendingWrites.clear();
				pendingWriteSnapshots.clear();
			}
		}

		public void run() {
			while (!interrupted()) {
				StreamEntitySignal snapshot = null;
				try {
					snapshot = writeQueue.poll(5, TimeUnit.SECONDS);
					if (snapshot == null && pendingWrites.size() > 0) {
						// write pending writes if no snapshot received for 15 seconds;
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						// metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
						//metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
						pendingWrites.clear();
						pendingWriteSnapshots.clear();
						continue;
					}
					if (snapshot == null) {
						continue;
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception pulling  snapshot  " + e.toString(), e);
				}
				try {

					Document document = MongoStreamConverter.signalToDocument(snapshot,
							descriptor.getTimeZone());
					
					pendingWrites.add(new InsertOneModel<Document>(document));
					pendingWriteSnapshots.add(snapshot);

				} catch (Exception e) {
					// errorLogCount.incrementAndGet();
					logger.error("Exception building Entity Snapshot Document " + e.toString());
					metrics.error("Exception Building Snapshot Document " + e.toString());
					continue;
				}
				if (pendingWrites.size() > bucketBatchSize) {
					try {
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();

					//	metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
						pendingWrites.clear();
						pendingWriteSnapshots.clear();

					} catch (Exception e) {
						logger.error("Exception bulk write entity signal " + e.toString(), e);
						metrics.error("Exception write entity signal " + e.toString());
					}
				}
			}

		}

	}

}
