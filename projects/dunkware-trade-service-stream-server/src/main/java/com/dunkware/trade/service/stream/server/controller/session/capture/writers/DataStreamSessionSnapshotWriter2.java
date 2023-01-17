package com.dunkware.trade.service.stream.server.controller.session.capture.writers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.stream.data.DataStreamSnapshotWriterSessionStats2;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.capture.StreamSessionCapture;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.TimeSeriesGranularity;
import com.mongodb.client.model.TimeSeriesOptions;

public class DataStreamSessionSnapshotWriter2 implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigService config;

	private BlockingQueue<GEntitySnapshot> writeQueue = new LinkedBlockingQueue<GEntitySnapshot>();

	private DKafkaByteConsumer2 kafkaConsumer;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	private MongoCollection<Document> snapshotCollection;

	private ZoneId timeZone;

	private SnapshotWriter snapshotWriter;
	private QueueMonitor queueMonitor;

	private StreamSessionCapture capture;
	private StreamSession session;

	private Map<String, AtomicInteger> entities = new ConcurrentHashMap<String, AtomicInteger>();

	private DataStreamSessionSnapshotWriterMetrics2 metrics;

	@Value("${snapshot.writer.batchsize}")
	private int bucketBatchSize;

	@Value("${snapshot.writer.queuelimit}")
	private int queueSizeLimit;

	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	private boolean completed = false;

	private int consumeCounter = 0;

	private GStreamSessionStop stopEvent;

	private String mongoCollectionName;

	volatile boolean paused = false;

	private boolean writerClosed = false;

	private Marker marker = MarkerFactory.getMarker("SnapshotWriter");
	
	private boolean terminated = false;

	public DataStreamSessionSnapshotWriter2() {

	}

	public void sessionStopped() {
		logger.debug("Snapshot Writer handling session stopped, starting session stopper");
		StreamSessionStopper stopper = new StreamSessionStopper();
		if(terminated) { 
			capture.snapshotWriterComplete();
		}
		stopper.start();
	}

	public void start(StreamSessionCapture capture) throws Exception {
		logger.info("Starting Data Snapshot writer");
		this.capture = capture;
		this.session = capture.getSession();
		metrics = new DataStreamSessionSnapshotWriterMetrics2();
		metrics.start(this);

		logger.info(MarkerFactory.getMarker(session.getSessionId()), "Starting Snapshot Writer");
		timeZone = DTimeZone.toZoneId(session.getStream().getTimeZone());
		try {
			
			mongoClient = MongoClients.create(config.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			
			String ident = session.getSessionId();
			System.out.println(ident);
			// check if the database exists
			LocalDateTime dt = LocalDateTime.now(DTimeZone.toZoneId(session.getStream().getTimeZone()));
			String Timestamp = DunkTime.format(dt, DunkTime.YYMMDD);
			mongoCollectionName = "snapshot_" + session.getStream().getName() + "_" + Timestamp;
			boolean exists = false; 
			for (String colName : mongoDatabase.listCollectionNames()) {
				if(colName.equals(mongoCollectionName)) {
					exists = true;
					break;
				}
			}
			if(!exists) { 
				logger.error("Creating Time Serries Snapshot Collection " + mongoCollectionName);
				TimeSeriesOptions tsOptions = new TimeSeriesOptions("time");
				tsOptions.granularity(TimeSeriesGranularity.SECONDS);
				tsOptions.metaField("vars");
				try {
					CreateCollectionOptions colOptions = new CreateCollectionOptions().timeSeriesOptions(tsOptions);
					mongoDatabase.createCollection(mongoCollectionName, colOptions);					
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

			logger.error(
					"Exception connecting stream {} session {} snapshot writer to mongo db ", session.getStream().getName(),
					session.getSessionId());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		String snapshotTopic = "stream_" + session.getStream().getName().toLowerCase() + "_event_snapshot";

		DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
				.setBrokerString(config.getKafkaBrokers()).addTopic(snapshotTopic)
				.setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
		logger.info(marker, "Consuming snapshot topics from " + snapshotTopic);
		try {

			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			// idiot start the fucking consumer
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
			logger.info("started kafka consumer on topic " + spec.getTopics());
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker(session.getSessionId()),
					"Exception connecting to kafka consumer " + e.toString(), e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}

		snapshotWriter = new SnapshotWriter();
		snapshotWriter.start();
		queueMonitor = new QueueMonitor();
		queueMonitor.start();
	}

	public StreamSession getSession() {
		return session;
	}

	public int getQueueSize() {
		return writeQueue.size();
	}

	public DataStreamSnapshotWriterSessionStats2 getStats() {
		return metrics.getStats();
	}

	public String getMongoURL() {
		return config.getMongoURL();
	}

	public String getMongoDatabase() {
		return config.getMongoDatabase();
	}

	public String getMongoCollection() {
		return mongoCollectionName;
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		GEntitySnapshot snapshot = null;
		try {
			event = GStreamEvent.parseFrom(record.value());
			//
			if (event.getType() == GStreamEventType.SessionStop) {
				stopEvent = event.getSessionStop();
				return;
			}
			if (event.getType() == GStreamEventType.EntitySnapshot) {
				if (consumeCounter == 0) {
					logger.info(marker, "Consumed First Snapshot Message From Kafka Topic");
				}
				consumeCounter++;

				snapshot = event.getEntitySnapshot();
				capture.entitySnapshot(snapshot);
				metrics.snapshotConsume(snapshot);
				writeQueue.put(snapshot);

			} else {
				return;
			}
		} catch (Exception e) {
			logger.error("Exception parsing snapshot from bytes " + e.toString());
			// errorLogCount.incrementAndGet();
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
					logger.error( "Exception monitoring queue size " + e.toString(), e);
				}

			}
		}
	}

	public boolean isCompleted() {
		if(terminated) { 
			return true;
		}
		return completed;
	}

	private class StreamSessionStopper extends Thread {
	
		public void run() {
			logger.debug("Starting SnapshotWrtier Stopper");
			logger.debug("Invoking Finished Writing method");
			int count = 1;
			while (!finishedWriting()) {
				try {
					Thread.sleep(1000);
					logger.debug("Not Finished Writing Count " + count);
					count++;
					if (count > 300) {
						logger.error("Stream Session Stopper Not Finished Writing After 10 minutes "
								+ "last snapshot write time is "
								+ DDateTime.toSqlTimestamp(metrics.getStats().getLastWriteTime())
								+ "force stopping writer");
						closeWriter();
						return;
					}

				} catch (Exception e) {
					logger.error("Exception in StremSessionStopper " + e.toString());
					if (!writerClosed)
						closeWriter();
					return;
				}
			}
			closeWriter();

		}

		private void closeWriter() {
			logger.debug("Closing Snapshot Writer");
			mongoClient.close();
			snapshotWriter.interrupt();
			snapshotWriter.writePendingSnapshots();
			queueMonitor.interrupt();
			metrics.setStopTime(DDateTime.now(session.getStream().getTimeZone()));
			kafkaConsumer.dispose();
			logger.info("Snapshot Writer invoking complete method on session");
			capture.snapshotWriterComplete();
			writerClosed = true;
		}

		public boolean finishedWriting() {

			while (true) {
				int count = 0;
				while (paused) {
					if (count == 0)
						logger.debug("SnapshotWriter Stopper Detected Consumer Paused");
					try {
						Thread.sleep(1000);
						count++;
					} catch (Exception e) {
					}
				}
				logger.debug("SnapshotWriter Stopper Detected Consumer Unpaused");
				count = 0;
				while (writeQueue.size() > 0) {
					try {
						if (count == 0)
							logger.debug("Snapshot Detector Detected Write Queue Size Not Empty");
						count++;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				logger.debug("Snapshot Stopper Detected Write Queue is empty");
				if (paused) {
					return false;
				}
				return true;
			}
		}
	}

	private class SnapshotWriter extends Thread {

		private DStopWatch sw = DStopWatch.create();
		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		private List<GEntitySnapshot> pendingWriteSnapshots = new ArrayList<GEntitySnapshot>();

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
				metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
				pendingWrites.clear();
				pendingWriteSnapshots.clear();
			}
		}

		public void run() {
			while (!interrupted()) {
				GEntitySnapshot snapshot = null;
				try {
					snapshot = writeQueue.poll(15, TimeUnit.SECONDS);
					if (snapshot == null && pendingWrites.size() > 0) {
						// write pending writes if no snapshot received for 15 seconds;
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						// metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
						metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
						pendingWrites.clear();
						pendingWriteSnapshots.clear();
						continue;
					}
					if (snapshot == null) {
						//
						continue;
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception pulling  snapshot  " + e.toString(), e);
				}
				try {

					Document document = DataStreamWriterHelper.buildSnapshot(snapshot,
							session.getStream().getTimeZone());
					if (entities.get(snapshot.getSessionId()) == null) {
						entities.put(snapshot.getSessionId(), new AtomicInteger(1));
						if (logger.isDebugEnabled()) {
							logger.debug(MarkerFactory.getMarker("NewEntitySnapshotWrite"), "{} {}",
									snapshot.getSessionId(), session.getSessionId());
						}
					} else {
						entities.get(snapshot.getSessionId()).incrementAndGet();
					}

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

						metrics.snapshotInsert(pendingWriteSnapshots, pendingWrites.size(), sw.getCompletedSeconds());
						pendingWrites.clear();
						pendingWriteSnapshots.clear();

					} catch (Exception e) {
						logger.error("Exception bulk write entity snapshot " + e.toString(), e);
						metrics.error("Exception write entity snapshot " + e.toString());
					}
				}
			}

		}

	}

}
