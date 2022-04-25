package com.dunkware.trade.service.data.service.stream.writers;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.stream.DataStream;
import com.dunkware.trade.service.data.service.stream.DataStreamSession;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class DataStreamSessionSnapshotWriter implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuntimeConfig config;

	private Map<String, DataStreamSessionSnapshotWriterBucket> buckets = new ConcurrentHashMap<String, DataStreamSessionSnapshotWriterBucket>();

	private BlockingQueue<DataStreamSessionSnapshotWriterBucket> writeQueue = new LinkedBlockingQueue<DataStreamSessionSnapshotWriterBucket>();

	private DKafkaByteConsumer2 kafkaConsumer;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	private MongoCollection<Document> snapshotCollection;

	private ZoneId timeZone;

	private BucketWriter bucketWriter;
	private QueueMonitor queueMonitor;
	private WriterLogger writeLogger;

	private DataStream stream;
	private DataStreamSession session;
	

	private DataStreamSessionSnapshotWriterMetrics metrics;
	
	@Value("${snapshot.writer.batchsize}")
	private int bucketBatchSize;

	@Value("${snapshot.writer.queuelimit}")
	private int queueSizeLimit;
	
	@Value("${kafka.brokers}")
	private String kafkaBrokers;

	private boolean completed = false;

	private GStreamSessionStop stopEvent;
	
	private String mongoCollectionName;

	public DataStreamSessionSnapshotWriter() {

	}

	public void start(DataStreamSession session) throws Exception {
		logger.info("Starting Data Snapshot writer");
		this.session = session;
		this.stream = session.getStream();
		metrics = new DataStreamSessionSnapshotWriterMetrics();
		
		metrics.start(this);
		
		logger.info(MarkerFactory.getMarker(session.getIdentifier()), "Starting Snapshot Writer");
		timeZone = DTimeZone.toZoneId(session.getStream().getTimeZone());
		try {
			mongoClient = MongoClients.create(config.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			String ident = session.getIdentifier();
			System.out.println(ident);
			mongoCollectionName = "stream_" + session.getStream().getName().toLowerCase() + "_snapshots";
			snapshotCollection = mongoDatabase
					.getCollection(mongoCollectionName)
					.withWriteConcern(wc);
			logger.info("Created mongo client");
		} catch (Exception e) {
		
			logger.error(DataMarkers.getServiceMarker(),
					"Exception connecting stream {} session {} snapshot writer to mongo db ", stream.getName(),
					session.getIdentifier());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}

		DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
				.setBrokerString(session.getSpec().getKafkaBrokers()).addTopic("stream_" + session.getStream().getName().toLowerCase() + "_event_snapshot")
				.setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();

		try {
			
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			// idiot start the fucking consumer
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
			logger.info("started kafka consumer on topic " + spec.getTopics());
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker(session.getIdentifier()),"Exception connecting to kafka consumer " + e.toString(), e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}

		bucketWriter = new BucketWriter();
		bucketWriter.start();
		queueMonitor = new QueueMonitor();
		queueMonitor.start();
		writeLogger = new WriterLogger();
		writeLogger.start();
		
		

	}
	
	public DataStreamSession getSession() { 
		return session;
	}

	public int getQueueSize() { 
		return writeQueue.size();
	}
	public DataStreamSnapshotWriterSessionStats getStats() { 
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
		logger.debug("Receieved GEntitySnapshot");
		GStreamEvent event = null;
		GEntitySnapshot snapshot = null;
		try {
			event = GStreamEvent.parseFrom(record.value());
			//
			if (event.getType() == GStreamEventType.SessionStop) {
		
				stopEvent = event.getSessionStop();
				WriterDisposer disposer = new WriterDisposer();
				disposer.start();
				return;
			}
			if (event.getType() == GStreamEventType.EntitySnapshot) {
				snapshot = event.getEntitySnapshot();
				session.entitySnapshot(snapshot);
				metrics.snapshotConsumed(snapshot);

			} else {
				return;
			}
		} catch (Exception e) {
			logger.error("Exception parsing snapshot from bytes " + e.toString());
			// errorLogCount.incrementAndGet();
			return;
		}

		if (buckets.get(snapshot.getIdentifier()) == null) {
			DataStreamSessionSnapshotWriterBucket bucket = new DataStreamSessionSnapshotWriterBucket();
			bucket.setStart(DProtoHelper.toLocalDateTime(snapshot.getTime(), session.getStream().getTimeZone()));
			if (logger.isDebugEnabled()) {
				logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket " + snapshot.getIdentifier()
						+ "Starting at " + DunkTime.format(bucket.getStart(), DunkTime.HH_MMM_SS));
			}
			bucket.setId(snapshot.getId());
			bucket.setIdentifier(snapshot.getIdentifier().toString());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
		} else {
			DataStreamSessionSnapshotWriterBucket bucket = buckets.get(snapshot.getIdentifier());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
			if (bucket.getSize() > 30) {
				try {
					
					bucket.setStop(DProtoHelper.toLocalDateTime(snapshot.getTime(), session.getStream().getTimeZone()));
					if (logger.isDebugEnabled()) {
						logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket {} Closing at {} sizei {}",
								bucket.getIdentifier(), DunkTime.formatHHMMSS(bucket.getStop()), bucket.getSize());
					}
					writeQueue.put(bucket);
					buckets.remove(snapshot.getIdentifier());
				} catch (Exception e) {
					// errorLogCount.incrementAndGet();
					logger.error("Exception putting snapshot bucket in write q " + e.toString());
					;
				}
			}
		}

	}

	private class WriterDisposer extends Thread {

		public void start() {
			
				 int count = 0;
				 kafkaConsumer.dispose();
				 while(writeQueue.isEmpty() == false) { 
					 try {
						Thread.sleep(1000);
						count++;
						
						if(count > 60) {
							logger.error(DataMarkers.getServiceMarker(), "Snapshot writer disposer timed out after snapshot write queue not emptied in 60 seconds");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				 }
				
				 bucketWriter.interrupt();
				 queueMonitor.interrupt();
				 writeLogger.interrupt();
				 session.snapshotWriterComplete();
				  
		}
	}

	private class WriterLogger extends Thread {

		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(30000);
					if(logger.isDebugEnabled()) { 
						logger.debug(MarkerFactory.getMarker(session.getIdentifier()), 
								"Consumed {} Snapshot Write {} Bucket Write {} Pause Time {} Last Consume Time {} Error Count {}",
								metrics.getEntitySnapshotConsumed(), metrics.getSnapshotWriteCount(), metrics.getPauseTime(),  metrics.getLastSnapshotConsumeTime(),metrics.getErrorCount());
					}
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Writer Logger Exception " + e.toString());
					;
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
					logger.error(DataMarkers.getServiceMarker(), "Exception monitoring queue size " + e.toString(), e);
				}

			}
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	private class BucketWriter extends Thread {

		private DStopWatch sw = DStopWatch.create();
		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		private List<DataStreamSessionSnapshotWriterBucket> pendingBuckets = new ArrayList<DataStreamSessionSnapshotWriterBucket>();
		public void run() {
			while (!interrupted()) {
				DataStreamSessionSnapshotWriterBucket bucket = null;
				try {
					bucket = writeQueue.poll(15, TimeUnit.SECONDS);
					if (bucket == null && pendingWrites.size() > 0) {
						// write pending writes if no snapshot received for 15 seconds;
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						//metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
						metrics.bucketWriteBatch(pendingBuckets, pendingWrites.size(),sw.getCompletedSeconds());
						pendingWrites.clear();
						pendingBuckets.clear();
						continue;
					}
					if (bucket == null) {
						continue;
					}

				} catch (Exception e) {
					// .DataStreamSessionSnapshotWriter.incrementAndGet();
					logger.error("Exception pulling bucket snapshot  " + e.toString(), e);
				}
				try {
	
					Document document = DataStreamWriterHelper.buildSnapshotBucket(bucket,
							session.getStream().getTimeZone());
					pendingWrites.add(new InsertOneModel<Document>(document));
					pendingBuckets.add(bucket);
					
				} catch (Exception e) {
					// errorLogCount.incrementAndGet();
					logger.error("Exception building Snapshot Bucket Document " + e.toString());
					metrics.error("Exception Building Snapshot Document " + e.toString());
					
					continue;
				}
				if (pendingWrites.size() > bucketBatchSize) {
					try {
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						
						metrics.bucketWriteBatch(pendingBuckets, pendingWrites.size(), sw.getCompletedSeconds());;
						pendingWrites.clear();
						pendingBuckets.clear();

					} catch (Exception e) {
						logger.error("Exception bulk write snapshot bucket " + e.toString(), e);
						metrics.error("Exception writing buckets " + e.toString());
					}
				}
			}

		}

	}

}
