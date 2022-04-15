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
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterStats;
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
	private DebugLogger debugLogger; 
	
	
	private DataStream stream; 
	private DataStreamSession session;
	
	private DataStreamSessionSnapshotWriterMetrics metrics; 
	
	@Value("${snapshot.writer.batchsize}")
	private int bucketBatchSize; 
	
	@Value("${snapshot.writer.queuelimit}")
	private int queueSizeLimit; 
	
	private boolean completed = false; 
	
	private GStreamSessionStop stopEvent; 
	
	public DataStreamSessionSnapshotWriter() {

	}
	

	public void start(DataStreamSession session) throws Exception {
		metrics = new DataStreamSessionSnapshotWriterMetrics(this);
		this.session = session;
		this.stream = session.getStream();
		
		timeZone = DTimeZone.toZoneId(session.getStream().getTimeZone());
		try {
			mongoClient = MongoClients.create(config.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			String ident = session.getIdentifier();
			System.out.println(ident);
			snapshotCollection = mongoDatabase.getCollection("stream_" + session.getStream().getName().toLowerCase() + "_snapshots").withWriteConcern(wc);

		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), "Exception connecting stream {} session {} snapshot writer to mongo db ",stream.getName(),session.getIdentifier());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		
		DKafkaConsumerSpec2 spec = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).setBrokerString("localhost:9091")
				.addTopic("stream_us_equity_snapshots").setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
				
		try {
			
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			// idiot start the fucking consumer
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
		
	}
	
	public void stop() { 
		
		Thread stopRunner = new Thread() { 
			
			public void run() { 
				int loopcount = 0;
				while(writeQueue.isEmpty() == false) { 
					try {
						Thread.sleep(500);
						loopcount++;
						if(loopcount > 120) { 
							logger.error(MarkerFactory.getMarker("Data"), "Stopping Snapshot Snapshot Writer with pending snapshots in q " +  writeQueue.size());
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				loopcount = 0;
				while(bucketWriter.queuedBuckets() > 0) {
					try {
						Thread.sleep(1000);
						loopcount++;
						if(loopcount > 30 ) {
							logger.error(DataMarkers.getServiceMarker(), "Waiting 30 seconds for bucket snapshot writer to write pending buckes something not right ");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				kafkaConsumer.dispose();
				bucketWriter.interrupt();
 				
				if(debugLogger != null) {
					debugLogger.interrupt();
				}
			}
			
		};
		stopRunner.start();
		
		
		return;
	}

	public DataStreamSnapshotWriterStats getStats() {
		DataStreamSnapshotWriterStats stats = new DataStreamSnapshotWriterStats();
		return stats;
	}
	
	

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		GEntitySnapshot snapshot = null;
		try {
		 event = GStreamEvent.parseFrom(record.value());	
		//
		 if(event.getType() == GStreamEventType.SessionStop) { 
			 stopEvent = event.getSessionStop();
			 stop();
			 return;
		 }
		 if(event.getType() == GStreamEventType.EntitySnapshot) { 
			 snapshot = event.getEntitySnapshot();
		
			 
		 } else { 
			 return;
		 }
		} catch (Exception e) {
			logger.error("Exception parsing snapshot from bytes " + e.toString());
		//	errorLogCount.incrementAndGet();
			return;
		}
		
		if (buckets.get(snapshot.getIdentifier()) == null) {
			DataStreamSessionSnapshotWriterBucket bucket = new DataStreamSessionSnapshotWriterBucket();
			bucket.setStart(DProtoHelper.toLocalDateTime(snapshot.getTime(),session.getStream().getTimeZone()));
			if(logger.isDebugEnabled()) { 
				logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket " + snapshot.getIdentifier() + "Starting at " + DunkTime.format(bucket.getStart(), DunkTime.HH_MMM_SS));
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
					if(logger.isDebugEnabled()) { 
						logger.debug(MarkerFactory.getMarker("Data"), "Snapshot Bucket {} Closing at {} sizei {}", bucket.getIdentifier(), DunkTime.formatHHMMSS(bucket.getStop()), bucket.getSize());
					}
					writeQueue.put(bucket);
					buckets.remove(snapshot.getIdentifier());
				} catch (Exception e) {
			//		errorLogCount.incrementAndGet();
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
						if(writeQueue.size() < queueSizeLimit) { 
							metrics.consumerResumed();
							kafkaConsumer.resumeConsumer();
							paused = false; 
						}
						continue;
						
					}
					if(writeQueue.size() > queueSizeLimit) { 
						metrics.consumerPaused();
						kafkaConsumer.pauseConsumer();
						paused = true;
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;		
					}
					logger.error(DataMarkers.getServiceMarker(), "Exception monitoring queue size " + e.toString(),e);
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
		public void run() { 
			while(!interrupted()) { 
				DataStreamSessionSnapshotWriterBucket bucket = null;
				try {
					bucket = writeQueue.poll(15, TimeUnit.SECONDS);
					if(bucket == null && pendingWrites.size() > 0) { 
						// write pending writes if no snapshot received for 15 seconds;
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
						
						pendingWrites.clear();
						continue;
					}
					if(bucket == null) { 
						continue;
					}
					 
				} catch (Exception e) {
				//	.DataStreamSessionSnapshotWriter.incrementAndGet();
					logger.error("Exception pulling bucket snapshot  " + e.toString(),e);
				}
				try {
					if(logger.isDebugEnabled()) { 
						
						logger.debug("Adding Entity Bucket to Write Q for {} Start Time {} Stop Time {} Size {}",bucket.getIdentifier(),DunkTime.format(bucket.getStart(), DunkTime.HH_MMM_SS), DunkTime.format(bucket.getStop(), DunkTime.HH_MMM_SS),bucket.getSnapshots().size());
						
					}
					Document document = DataStreamWriterHelper.buildSnapshotBucket(bucket,session.getStream().getTimeZone());
					pendingWrites.add(new InsertOneModel<Document>(document));
				} catch (Exception e) {
					//errorLogCount.incrementAndGet();
					logger.error("Exception building Snapshot Bucket Document " + e.toString());
					continue;
				}
				if(pendingWrites.size() > 0) { 
					try {
						sw.start();
						snapshotCollection.bulkWrite(pendingWrites);
						sw.stop();
						metrics.bucketWriteBatch(pendingWrites.size(), bucketBatchSize);
						
						pendingWrites.clear();
						
					} catch (Exception e) {
						logger.error("Exception bulk write snapshot bucket " + e.toString(),e);
						//errorLogCount.incrementAndGet();
					}
				}
			}

			
		}
		
		public int queuedBuckets() { 
			return pendingWrites.size();
		}
	}
	
	

}
