package com.dunkware.trade.service.data.service.stream.writers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.stream.DataStreamSession;
import com.dunkware.trade.service.data.service.util.NameHelper;
import com.dunkware.xstream.data.cache.CacheException;
import com.dunkware.xstream.data.capture.MongoCaptureHelper;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

public class DataStreamSignalWriter implements DKafkaByteHandler2 {

	@Autowired
	private RuntimeConfig configService;

	private DataStreamSession session;
	private DKafkaByteConsumer2 consumer;

	private BlockingQueue<GEntitySignal> writeQueue = new LinkedBlockingQueue<GEntitySignal>();

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private AtomicLong signalConsumeCount = new AtomicLong();
	private AtomicLong signalWriteCount = new AtomicLong();

	private LocalTime lastSignalConsume = null;
	private LocalTime lastSignalWrite = null;
	
	private int lastSignalWriteSize =0;
	private double lastSignalWriteTime = 0;

	private BlockingQueue<GEntitySignal> signalQueue = new LinkedBlockingQueue<GEntitySignal>();
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Writer writer; 
	
	
	public void start(DataStreamSession session) throws Exception {
		this.session = session;

		try {
			String url = configService.getMongoURL();
			mongoClient = MongoClients.create(url);
			WriteConcern wc = new WriteConcern(0).withJournal(false);
			mongoDatabase = mongoClient.getDatabase(configService.getMongoDatabase());
			signalCollection = mongoDatabase
					.getCollection(
							NameHelper.getSessionSignalCollectionName(this.session.getSpec().getStreamIdentifier()))
					.withWriteConcern(wc);
		} catch (Exception e) {
			throw new Exception("Exception init session signal writer  " + e.getLocalizedMessage(), e);
		}

		DKafkaConsumerSpec2 spec = null;
		try {
			spec = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.Auto, OffsetType.Latest)
					.addBroker(session.getSpec().getKafkaBrokers()).addTopic(session.getSpec().getKafkaSignalTopic())
					.setClientAndGroup(DUUID.randomUUID(5),
							DUUID.randomUUID(3)).build();
					
		} catch (Exception e) {
			throw new CacheException("Exception building cache kafka snapshot consumer spec " + e.toString(), e);
		}
		try {
			consumer = DKafkaByteConsumer2.newInstance(spec);
			consumer.start();
			consumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new Exception("Exception starting stream session signal writer " + e.toString());
		}
		
		writer = new Writer();
		writer.start();
	}
	
	public void stop() { 
		int loopcount = 0;
		while(signalQueue.isEmpty() == false) { 
			try {
				Thread.sleep(500);
				loopcount++;
				if(loopcount > 10) { 
					logger.error(MarkerFactory.getMarker("Data"), "Stopping Snapshot Signal Writer with pending signals in q " + signalQueue.size());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		writer.interrupt();
		consumer.dispose();
		mongoClient.close();
		return;
	}

	public DataStreamSignalWriter getStats() { 
		DataStreamSignalWriter stats = new DataStreamSignalWriter();
		return stats;
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
					lastSignalConsume = LocalTime.now(DTimeZone.toZoneId(session.getSpec().getTimeZone()));
				} catch (Exception e) {
					logger.error("Exception putting signal in q " + e.toString());
				}

			} else {
				return;
			}
		} catch (Exception e) {
			logger.error("Exception parsing signal from bytes " + e.toString());
			return;
		}

	}

	private class Writer extends Thread {

		private List<InsertOneModel<Document>> pendingWrites = new ArrayList<InsertOneModel<Document>>();

		public void run() {
			DStopWatch watch = DStopWatch.create();
			while (!interrupted()) {
				try {
					GEntitySignal signal = signalQueue.poll(15, TimeUnit.SECONDS);
					if (signal == null) {
						if (pendingWrites.size() > 0) {
							watch.start();
							signalCollection.bulkWrite(pendingWrites);
							watch.stop();
							lastSignalWriteTime = watch.getCompletedSeconds();
							lastSignalWriteSize = pendingWrites.size();
							
							signalWriteCount.addAndGet(pendingWrites.size());
							lastSignalWrite = LocalTime.now(DTimeZone.toZoneId(session.getSpec().getTimeZone()));
							pendingWrites.clear();
						}
						continue;
					}
					Document doc = null;
					try {
						doc = MongoCaptureHelper.buildEntitySignal(signal, session.getSpec().getTimeZone());
						pendingWrites.add(new InsertOneModel<Document>(doc));
					} catch (Exception e) {
						logger.error(MarkerFactory.getMarker("Data"),
								"Exception creating mongo document from signal event " + e.toString());
						continue;
					}
					if (pendingWrites.size() > 30) {
						watch.start();
						signalCollection.bulkWrite(pendingWrites);
						watch.stop();
						lastSignalWriteTime = watch.getCompletedSeconds();
						lastSignalWriteSize = pendingWrites.size();
						signalWriteCount.addAndGet(pendingWrites.size());
						pendingWrites.clear();
					}

				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error(MarkerFactory.getMarker("Data"),"Outer Exception in session signal writer persister " + e.toString());
				}

			}
		}
	}

}
