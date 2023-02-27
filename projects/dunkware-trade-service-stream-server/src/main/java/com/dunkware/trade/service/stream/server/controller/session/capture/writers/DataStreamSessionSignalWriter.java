package com.dunkware.trade.service.stream.server.controller.session.capture.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSignalWriterSessionStats;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.capture.StreamSessionCaptureExt;
import com.dunkware.trade.service.stream.server.spring.ConfigService;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.InsertOneModel;

public class DataStreamSessionSignalWriter implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigService config;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private LinkedBlockingQueue<GEntitySignal> writeQueue = new LinkedBlockingQueue<GEntitySignal>();

	private DTimeZone timeZone;


	private StreamController stream;
	private String sessionIdentifier;

	private DataStreamSessionSignalWriterMetrics metrics;

	private StreamSessionCaptureExt capture;
	
	private SignalWriter writer;
	
	private DKafkaByteConsumer2 kafkaConsumer;

	private Marker marker = MarkerFactory.getMarker("stream.session.capture");
	
	public void startSession(StreamSessionCaptureExt capture) throws Exception {

		this.stream = capture.getStream();
		this.capture = capture;;
		
		this.timeZone = capture.getSpec().getTimeZone();
		
		metrics = new DataStreamSessionSignalWriterMetrics();
		metrics.start(this);
		// if pending set the start time and all that good stuff.

		// create a mongo database writer
		try {
			mongoClient = MongoClients.create(config.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			boolean collectionExists = false; 
			String signalCollectionName = "stream_" + stream.getName() + "_signals";
			for (String collection : mongoDatabase.listCollectionNames()) {
				if(collection.equals(signalCollectionName)) { 
					collectionExists = true; 
					break;
				}
			}
			if(!collectionExists) { 
				 mongoDatabase.createCollection(signalCollectionName);
				 signalCollection = mongoDatabase.getCollection(signalCollectionName);
				 signalCollection.createIndex(Indexes.ascending("timestamp"));
				 signalCollection.createIndex(Indexes.ascending("ident"));
				 signalCollection.createIndex(Indexes.ascending("eident"));
			} else { 
				signalCollection = mongoDatabase.getCollection("stream_" + stream.getName() + "_signals");	
			}
			
		} catch (Exception e) {
		
			logger.error("Exceptoon Init Mongo DB in Session Singal Writer  " + e.toString());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		
		DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).setBrokerString(capture.getSpec().getKafkaBrokers())
				.addTopic("stream_" + capture.getSession().getStream().getName().toLowerCase() + "_event_signal").setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
				
		try {
			
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
		
			logger.error("Exception connecting to kafka consumer " + e.toString(),e);
			throw new Exception("Exception creating kafka byte consumer " + e.toString());
		}
		
		writer = new SignalWriter();
		writer.start();

	}
	
	public void closeWriter() { 
		// okay here we will block 
		// for business to finish to a point. 
		int count = 0;
		kafkaConsumer.dispose();
		while(writeQueue.isEmpty() == false) { 
			try {
				Thread.sleep(250);
				count++;
				if(count > 20) {
					logger.error(marker, "Signal Writer Queue is not empty after 5 seconds, force closing");
					disposeWriter();
					return;
				}
			} catch (Exception e) {
				logger.error(marker, "Outer exception in close writer " + e.toString());
			}
		}
		disposeWriter();
		
	}
	
	public void disposeWriter() { 
		writer.interrupt();
		writer.writePendingSignals();
		metrics.stop();
		mongoClient.close();

	}
	
	public DataStreamSignalWriterSessionStats getStats() { 
		return metrics.getStats();
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		try {
			event = GStreamEvent.parseFrom(record.value());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(event.getType() == GStreamEventType.EntitySignal) {
			GEntitySignal signal = event.getEntitySignal();
			capture.entitySignal(signal);
			writeQueue.add(signal);
		}
	}
	

	
	public int getWriteQueueSize() { 
		return writeQueue.size();
	}
	
	public StreamSessionCaptureExt getCapture() { 
		return capture;
	}

	private class SignalWriter extends Thread {

		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();
		private List<GEntitySignal> pendingSignalWrites = new ArrayList<GEntitySignal>();
		
		public void writePendingSignals() { 
			if(pendingSignalWrites.size() > 0) {
				signalCollection.bulkWrite(pendingWrites);
				pendingWrites.clear();
				pendingSignalWrites.clear();
			}
		}
		
		public void run() {
			DStopWatch watch = DStopWatch.create();
			while (!interrupted()) {
				try {
					// just publish all signals received every 5 seconds
					Thread.sleep(1000);
					List<GEntitySignal> signals = new ArrayList<GEntitySignal>();
					writeQueue.drainTo(signals);
					if (signals.size() == 0) {
						continue;
					}
					try {
						for (GEntitySignal gEntitySignal : signals) {
							Document signalDocument = DataStreamWriterHelper.buildEntitySignal(gEntitySignal,
									stream.getTimeZone());
							pendingWrites.add(new InsertOneModel<Document>(signalDocument));
							pendingSignalWrites.add(gEntitySignal);
						}
						
						try {
							watch.start();
							signalCollection.bulkWrite(pendingWrites);
							watch.stop();
							metrics.setLastBatchWrite(pendingSignalWrites, pendingWrites.size(), watch.getCompletedSeconds());
							pendingWrites.clear();
							pendingSignalWrites.clear();
						} catch (Exception e) {
							logger.error(
									"Exception Bulk Write Stream {} Session {} Signals " + e.toString(),
									stream.getName(), sessionIdentifier);
						}
					} catch (Exception e) {
						logger.error("Errror wriring sigal " + e.toString());
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error(MarkerFactory.getMarker("Data"),
							"Outer Exception in session signal writer persister " + e.toString());
				}

			}
		}
	}

}
