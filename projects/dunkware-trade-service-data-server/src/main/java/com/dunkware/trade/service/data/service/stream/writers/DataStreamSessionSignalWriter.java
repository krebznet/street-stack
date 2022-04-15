package com.dunkware.trade.service.data.service.stream.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
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

public class DataStreamSessionSignalWriter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RuntimeConfig config;

	@Autowired
	private DataServiceRepository dataRepo;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> signalCollection;

	private LinkedBlockingQueue<GEntitySignal> writeQueue = new LinkedBlockingQueue<GEntitySignal>();

//	private DataStreamSessionSignalsEntity entity;

	private DTimeZone timeZone;

	private volatile int statSignalsConsumed = 0;
	private volatile int statSignalsWritten = 0;
	private volatile int statLastWriteSize = 0;

	private DataStream stream;
	private String sessionIdentifier;
	
	private SignalWriter signalWriter = new SignalWriter();

	public void startSession(DataStreamSession session)
			throws Exception {
		
	
		// entity has already been persisted by the writer
	//	this.entity = entity;
		this.stream = stream;
		//this.sessionIdentifier = entity.getSesionIdentifier();
		timeZone = stream.getTimeZone();

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
		// create and start the signal persister
		signalWriter = new SignalWriter();
		signalWriter.start();
		
		//logger.info(DataMarkers.getServiceMarker(), "Started Signal Writer Session stream {}, session{}",stream.getName(),entity.getSesionIdentifier());

	}

	public void completeSession() {
		// see if we need to write any pending
		// signals
		int loops = 0;
		while (writeQueue.isEmpty() == false) {
			try {
				Thread.sleep(1000);
				loops++;
				if (loops > 6) {
					logger.error("Exception waiting on signal write quee to become empty after 60 seconds ");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		//this.entity.setState(DataStreamWriterSessionState.Completed);
		//this.entity.setSignalCount(statSignalsWritten);
		//this.entity.setCompleteDateTime(LocalDateTime.now(DTimeZone.toZoneId(stream.getTimeZone())));

		
		if(signalWriter != null) { 
			signalWriter.interrupt();
		}
		mongoClient.close();
		//logger.info(DataMarkers.getServiceMarker(), "Completed Signal Writer Session stream {}, session{}",stream.getName(),entity.getSesionIdentifier());

	}

	public void signal(GEntitySignal signal) {
		statSignalsConsumed++;
		writeQueue.add(signal);
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
					statLastWriteSize = signals.size();
					try {
						for (GEntitySignal gEntitySignal : signals) {
							Document signalDocument = MongoCaptureHelper.buildEntitySignal(gEntitySignal,
									stream.getTimeZone());
							pendingWrites.add(new InsertOneModel<Document>(signalDocument));
						}
						if(logger.isTraceEnabled()) { 
				//			logger.trace(DataMarkers.getServiceMarker(), "Signal Stream {} Session {} BulkWrite Size {}", stream.getName(),entity.getSesionIdentifier(),signals.size());
						}
						try {
							signalCollection.bulkWrite(pendingWrites);
							pendingWrites.clear();
						} catch (Exception e) {
							logger.error(DataMarkers.getServiceMarker(), "Exception Bulk Write Stream {} Session {} Signals " + e.toString(),stream.getName(),sessionIdentifier);
						}
						
						statSignalsWritten = statSignalsWritten = signals.size();
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
