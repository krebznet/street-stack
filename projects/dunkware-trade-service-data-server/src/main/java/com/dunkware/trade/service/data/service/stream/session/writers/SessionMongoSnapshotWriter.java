package com.dunkware.trade.service.data.service.stream.session.writers;

import java.time.LocalDateTime;
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
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.data.json.data.stream.session.StreamSessionSnapshotWriterStats;
import com.google.protobuf.Timestamp;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class SessionMongoSnapshotWriter implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, SessionMongoWriterBucket> buckets = new ConcurrentHashMap<String, SessionMongoWriterBucket>();

	private BlockingQueue<SessionMongoWriterBucket> writeQueue = new LinkedBlockingQueue<SessionMongoWriterBucket>();

	private AtomicInteger snapshotBucketConsumeCount = new AtomicInteger();
	private AtomicInteger snapshotBucketWriteCount = new AtomicInteger();
	private AtomicInteger errorLogCount = new AtomicInteger();
	private AtomicInteger consumeCount = new AtomicInteger();
	private AtomicInteger pauseCount = new AtomicInteger();
	
	private DKafkaByteConsumer2 kafkaConsumer; 

	private SessionMongoSnapshotWriterInput input;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> snapshotCollection;
	
	private double lastWriteTime = 0;

	private boolean running = false;
	
	private ZoneId timeZone;
	
	private BucketWriter bucketWriter; 
	private QueueMonitor queueMonitor; 
	private DebugLogger debugLogger; 
	
	

	public SessionMongoSnapshotWriter() {

	}
	

	public void start(SessionMongoSnapshotWriterInput input) throws Exception {
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
	
	public void stop() { 
		int loopcount = 0;
		while(writeQueue.isEmpty() == false) { 
			try {
				Thread.sleep(500);
				loopcount++;
				if(loopcount > 20) { 
					logger.error(MarkerFactory.getMarker("Data"), "Stopping Snapshot Snapshot Writer with pending signals in q " +  writeQueue.size());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		queueMonitor.interrupt();
		bucketWriter.interrupt();
		kafkaConsumer.dispose();
		mongoClient.close();
		if(debugLogger != null) {
			debugLogger.interrupt();
		}
		return;
	}

	public StreamSessionSnapshotWriterStats getStats() {
		StreamSessionSnapshotWriterStats stats = new StreamSessionSnapshotWriterStats();
		stats.setBucketWriteCount(this.snapshotBucketWriteCount.get());
		stats.setSnapshotCount(this.consumeCount.get());
		stats.setPauseCount(this.pauseCount.get());
		stats.setQueueSize(this.writeQueue.size());
		stats.setBucketWriteTime(lastWriteTime);
		stats.setBucketWriteSize(31);
		return stats;
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		GEntitySnapshot snapshot = null;
		try {
		 event = GStreamEvent.parseFrom(record.value());	
		 consumeCount.incrementAndGet();
		 if(event.getType() == GStreamEventType.EntitySnapshot) { 
			 snapshot = event.getEntitySnapshot();
			 if(logger.isDebugEnabled()) { 
				LocalDateTime dt = DunkTime.toLocalDateTime(snapshot.getTime(), input.getTimeZone());
				 logger.debug(MarkerFactory.getMarker("Data"), "Consumed Snapshot Entity {} at {} ", snapshot.getIdentifier(), DunkTime.formatHHMMSS(dt));
			 }
			 
			
			 
		 } else { 
			 return;
		 }
		} catch (Exception e) {
			logger.error("Exception parsing snapshot from bytes " + e.toString());
			errorLogCount.incrementAndGet();
			return;
		}
		
		if (buckets.get(snapshot.getIdentifier()) == null) {
			SessionMongoWriterBucket bucket = new SessionMongoWriterBucket();
			bucket.setStart(DProtoHelper.toLocalDateTime(snapshot.getTime(), input.getTimeZone()));
			if(logger.isDebugEnabled()) { 
				logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket " + snapshot.getIdentifier() + "Starting at " + DunkTime.format(bucket.getStart(), DunkTime.HH_MMM_SS));
			}
			bucket.setId(snapshot.getId());
			bucket.setIdentifier(snapshot.getIdentifier().toString());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
		} else {
			SessionMongoWriterBucket bucket = buckets.get(snapshot.getIdentifier());
			bucket.addSnapshot(snapshot);
			buckets.put(snapshot.getIdentifier(), bucket);
			if (bucket.getSize() == 31) {
				try {
					
					bucket.setStop(DProtoHelper.toLocalDateTime(snapshot.getTime(), input.getTimeZone()));
					if(logger.isDebugEnabled()) { 
						logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket {} Closing at {} sizei {}", bucket.getIdentifier(), DunkTime.formatHHMMSS(bucket.getStop()), bucket.getSize());
					}
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
							
							kafkaConsumer.resumeConsumer();
							paused = false; 
						}
						continue;
						
					}
					if(writeQueue.size() > input.getWriteQueueSizeLimit()) { 
						
						kafkaConsumer.pauseConsumer();
						pauseCount.incrementAndGet();
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
				SessionMongoWriterBucket bucket = null;
				try {
					 bucket = writeQueue.take();
					 
				} catch (Exception e) {
					errorLogCount.incrementAndGet();
					logger.error("Exception pulling bucket snapshot  " + e.toString(),e);
				}
				try {
					if(logger.isDebugEnabled()) { 
						
						logger.debug("Adding Entity Bucket to Write Q for {} Start Time {} Stop Time {} Size {}",bucket.getIdentifier(),DunkTime.format(bucket.getStart(), DunkTime.HH_MMM_SS), DunkTime.format(bucket.getStop(), DunkTime.HH_MMM_SS),bucket.getSnapshots().size());
						
					}
					Document document = SessionMongoWriterHelper.buildSnapshotBucket(bucket,input.getTimeZone());
					pendingWrites.add(new InsertOneModel<Document>(document));
				} catch (Exception e) {
					errorLogCount.incrementAndGet();
					logger.error("Exception building Snapshot Bucket Document " + e.toString());
					continue;
				}
				if(pendingWrites.size() > 1) { 
					try {
						sw.start();
						
						
						snapshotCollection.bulkWrite(pendingWrites);
						snapshotBucketWriteCount.incrementAndGet();
						sw.stop();
						lastWriteTime = sw.getCompletedSeconds();
						
						
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
