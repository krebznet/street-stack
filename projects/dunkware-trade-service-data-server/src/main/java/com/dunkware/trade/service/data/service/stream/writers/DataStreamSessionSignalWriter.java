package com.dunkware.trade.service.data.service.stream.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.stream.DataStream;
import com.dunkware.trade.service.data.service.stream.DataStreamSession;
import com.dunkware.trade.service.data.service.util.DataMarkers;
import com.dunkware.xstream.data.capture.MongoCaptureHelper;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class DataStreamSessionSignalWriter implements DKafkaByteHandler2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuntimeConfig config;
	
	

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private LinkedBlockingQueue<GEntitySignal> writeQueue = new LinkedBlockingQueue<GEntitySignal>();

	private DTimeZone timeZone;

	private DataStream stream;
	private String sessionIdentifier;

	private DataStreamSessionSignalWriterMetrics metrics;

	private DataStreamSession session;
	
	private SignalWriter writer;
	
	private DKafkaByteConsumer2 kafkaConsumer;

	
	public void startSession(DataStreamSession session) throws Exception {
		this.session = session;
		this.stream = session.getStream();
		this.timeZone = session.getSpec().getTimeZone();
		timeZone = stream.getTimeZone();
	
		metrics = new DataStreamSessionSignalWriterMetrics();
		metrics.start(this);
		// if pending set the start time and all that good stuff.

		// create a mongo database writer
		try {
			mongoClient = MongoClients.create(config.getMongoURL());
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(config.getMongoDatabase());
			signalCollection = mongoDatabase.getCollection("stream_" + stream.getName() + "_signals");

		} catch (Exception e) {
		
			logger.error("Exceptoon Init Mongo DB " + e.toString());
			throw new Exception("Mongo Setup/Connection Exception " + e.getLocalizedMessage(), e);
		}
		
		DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).setBrokerString("localhost:9091")
				.addTopic("stream_us_equity_signals").setClientAndGroup("d" + DUUID.randomUUID(5), "d" + DUUID.randomUUID(6)).build();
				
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
			session.entitySignal(signal);
			writeQueue.add(signal);
		}

		if(event.getType() == GStreamEventType.SessionStop) {
			
		
			
			DisposeRunner runner = new  DisposeRunner();
			runner.start();
		}
	}
	
	private class DisposeRunner extends Thread { 
		
		public void start() { 
			int count = 0;
			kafkaConsumer.dispose();
			while(writeQueue.isEmpty() == false) { 
				try {
					Thread.sleep(500);
					count++;
					if(count > 20) {
						logger.error(DataMarkers.getServiceMarker(), "Signal Writer Q Not Empty After 10 Seconds");
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			writer.interrupt();
			metrics.stop();
			mongoClient.close();
		
			session.signalWriterComplete();
			
		}
	}

	private class SignalWriter extends Thread {

		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();

		public void run() {
			DStopWatch watch = DStopWatch.create();
			while (!interrupted()) {
				try {
					// just publish all signals received every 5 seconds
					Thread.sleep(5000);
					List<GEntitySignal> signals = new ArrayList<GEntitySignal>();
					writeQueue.removeAll(signals);
					if (signals.size() == 0) {
						continue;
					}
					metrics.setLastBatchWriteSize(signals.size());
					try {
						for (GEntitySignal gEntitySignal : signals) {
							Document signalDocument = MongoCaptureHelper.buildEntitySignal(gEntitySignal,
									stream.getTimeZone());
							pendingWrites.add(new InsertOneModel<Document>(signalDocument));
						}
						
						try {
							watch.start();
							signalCollection.bulkWrite(pendingWrites);
							watch.stop();
							metrics.setLastBatchWriteSpeed(watch.getCompletedSeconds());
							pendingWrites.clear();
						} catch (Exception e) {
							logger.error(DataMarkers.getServiceMarker(),
									"Exception Bulk Write Stream {} Session {} Signals " + e.toString(),
									stream.getName(), sessionIdentifier);
						}
					} catch (Exception e) {
						// TODO: handle exception
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
