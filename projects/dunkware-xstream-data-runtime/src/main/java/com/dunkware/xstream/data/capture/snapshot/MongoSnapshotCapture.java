package com.dunkware.xstream.data.capture.snapshot;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
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
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.data.capture.MongoCaptureHelper;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class MongoSnapshotCapture implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, MongoSnapshotBucket> buckets = new ConcurrentHashMap<String, MongoSnapshotBucket>();

	private BlockingQueue<MongoSnapshotBucket> writeQueue = new LinkedBlockingQueue<MongoSnapshotBucket>();

	private AtomicInteger snapshotBucketWriteCount = new AtomicInteger();
	private AtomicInteger snapshotWriteCount = new AtomicInteger();
	private AtomicInteger errorLogCount = new AtomicInteger();
	
	private DKafkaByteConsumer2 kafkaConsumer; 

	private MongoSnapshotCaptureInput input;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> snapshotCollection;
	
	private double lastWriteTime = 0;

	private boolean running = false;
	
	private ZoneId timeZone;
	
	private BucketWriter bucketWriter; 
	private QueueMonitor queueMonitor; 
	private DebugLogger debugLogger; 
	

	public MongoSnapshotCapture() {

	}

	public void start(MongoSnapshotCaptureInput input) throws Exception {
		this.input = input;
		timeZone = DTimeZone.toZoneId(input.getTimeZone());
		try {
			mongoClient = MongoClients.create(input.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(input.getMongoDatabase());
			snapshotCollection = mongoDatabase.getCollection(input.getMongoCollection()).withWriteConcern(wc);

		} catch (Exception e) {
			logger.error("Exceptoon Init Mongo DB " + e.toString());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		
		try {
			kafkaConsumer = DKafkaByteConsumer2.newInstance(input.getKafkaSpec());
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			logger.error("Exception connecting to kafka consumer " + e.toString(),e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}
		
		bucketWriter = new BucketWriter();
		bucketWriter.start();
		queueMonitor = new QueueMonitor();
		queueMonitor.start();
		if(input.isDebugLogging()) { 
			debugLogger = new DebugLogger();
			debugLogger.start();
		}
	}

	public MongoSnapshotCaptureStats getStats() {
		MongoSnapshotCaptureStats stats = new MongoSnapshotCaptureStats();
		stats.setCaptureId(input.getCaptureId());
		stats.setStream(input.getStream());
		stats.setBucketWriteCount(snapshotBucketWriteCount.get());
		stats.setSnapshotWriteCount(snapshotWriteCount.get());
		stats.setQueueSize(writeQueue.size());
		stats.setLastWriteTime(lastWriteTime);
		stats.setErrorLogCount(errorLogCount.get());
		stats.setRecordCount(kafkaConsumer.getRecordCount());
		stats.setWriteCount(0);
		return stats;
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		GEntitySnapshot snapshot = null;
		try {
		 event = GStreamEvent.parseFrom(record.value());	
		 
		 if(event.getType() == GStreamEventType.EntitySnapshot) { 
			 snapshot = event.getEntitySnapshot();
			 
			
			 
		 } else { 
			 return;
		 }
		} catch (Exception e) {
			logger.error("Exception parsing snapshot from bytes " + e.toString());
			errorLogCount.incrementAndGet();
			return;
		}
		
		if (buckets.get(snapshot.getIdentifier()) == null) {
			MongoSnapshotBucket bucket = new MongoSnapshotBucket();
			bucket.setStart(DProtoHelper.toLocalDateTime(snapshot.getTime(), input.getTimeZone()));
			bucket.setId(snapshot.getId());
			bucket.setIdentifier(snapshot.getIdentifier().toString());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
		} else {
			MongoSnapshotBucket bucket = buckets.get(snapshot.getIdentifier());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
			if (bucket.getSize() == 30) {
				try {
					
					bucket.setStop(DProtoHelper.toLocalDateTime(snapshot.getTime(), input.getTimeZone()));
					writeQueue.put(bucket);
					buckets.remove(snapshot.getIdentifier());
				} catch (Exception e) {
					errorLogCount.incrementAndGet();
					logger.error("Exception putting snapshot bucket in write q " + e.toString());
					;
				}
			}
		}
		
		
		
	}


	
	private class DebugLogger extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
					MongoSnapshotCaptureStats stats = getStats();
					logger.debug("Snapshot Stream:{} Writer:{} SnapshotCount:{} BucketCount:{} ErrorCount:{}, LastWriteTime{}",stats.getStream(),stats.getSnapshotWriteCount(),stats.getBucketWriteCount(),stats.getErrorLogCount(),stats.getLastWriteTime());
					System.out.println("Snapshots " + snapshotWriteCount.get() + " Buckets " + snapshotBucketWriteCount.get() + " last write time " + lastWriteTime + " q size " + writeQueue.size());
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Debug Logger Exception " + e.toString());;
				}
			}
		}
	}
	
	private class QueueMonitor extends Thread { 
		
		volatile boolean paused = false; 
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(500);
					if(paused) { 
						if(writeQueue.size() < input.getWriteQueueSizeLimit()) { 
							System.out.println("resuming consumer");
							kafkaConsumer.resumeConsumer();
							paused = false; 
						}
						continue;
						
					}
					if(writeQueue.size() > input.getWriteQueueSizeLimit()) { 
						System.out.println("pausing consumer");
						kafkaConsumer.pauseConsumer();
						paused = true;
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;		
					}
					logger.error("Exception monitoring queue size " + e.toString(),e);
					errorLogCount.incrementAndGet();
				}
				
				
			}
		}
	}
	
	
	private class BucketWriter extends Thread { 
		
		private DStopWatch sw = DStopWatch.create();
		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		public void run() { 
			while(!interrupted()) { 
				MongoSnapshotBucket bucket = null;
				try {
					 bucket = writeQueue.take();
				} catch (Exception e) {
					errorLogCount.incrementAndGet();
					logger.error("Exception pulling bucket snapshot  " + e.toString(),e);
				}
				try {
					Document document = MongoCaptureHelper.buildSnapshotBucket(bucket,input.getTimeZone());
					pendingWrites.add(new InsertOneModel<Document>(document));
				} catch (Exception e) {
					errorLogCount.incrementAndGet();
					logger.error("Exception building Snapshot Bucket Document " + e.toString());
					continue;
				}
				if(pendingWrites.size() > 100) { 
					try {
						sw.start();
						System.out.println("wirting one bucket");
						snapshotWriteCount.addAndGet(pendingWrites.size());
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						lastWriteTime = sw.getCompletedSeconds();
						snapshotBucketWriteCount.incrementAndGet();
						pendingWrites.clear();
						if(logger.isDebugEnabled()) { 
							logger.debug("Snapshot Bucket Bulk Write Size {} took {} seconds",input.getBatchSize(),sw.getCompletedSeconds());;
						}
					} catch (Exception e) {
						logger.error("Exception bulk write snapshot bucket " + e.toString(),e);
						errorLogCount.incrementAndGet();
					}
				}
			}

			
		}
	}
	
	

}
