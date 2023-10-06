package com.dunkware.trade.net.data.server.stream.signals.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.service.data.model.search.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.net.data.server.stream.converters.MongoStreamConverter;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignals;
import com.dunkware.trade.net.data.server.stream.signals.injestor.StreamSignalIngestor;
import com.dunkware.trade.net.data.server.stream.signals.session.StreamSessionSignals;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProvider;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.mongodb.client.MongoCursor;

public class StreamSignalsImpl implements StreamSignals {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignals");

	@Autowired
	private ApplicationContext ac;

	private StreamSignalIngestor signalIngestor;

	private StreamDataProvider dataProvider;
	private StreamDescriptor descriptor;

	private StreamSessionSignals sessionSignals;

	public void init(StreamDataProvider dataProvider) throws Exception {
		logger.info(marker, "StreamSignals Impl");
		this.dataProvider = dataProvider;
		this.descriptor = dataProvider.getDescriptor();
		logger.info(marker, "Starting {} Stream Signals ", descriptor.getIdentifier());
		try {
			signalIngestor = new StreamSignalIngestor();
			signalIngestor.start(this);
		} catch (Exception e) {
			logger.error(marker, "Exception starting stream signal ingjestor " + e.toString());
			throw new Exception("cannot start signal ingestor " + e.toString());
		}

		signalIngestor = new StreamSignalIngestor();
		signalIngestor.start(this);

		sessionSignals = new StreamSessionSignals();
		ac.getAutowireCapableBeanFactory().autowireBean(sessionSignals);
		sessionSignals.init(this);
	}

	@Override
	public String getStreamIdentifier() {
		return descriptor.getIdentifier();
	}

	@Override
	public StreamDescriptor getStreamDescriptor() {
		return descriptor;
	}

	@Override
	public void insertSignal(StreamEntitySignal signal) throws Exception {
		try {
			Document document = MongoStreamConverter.signalToDocument(signal);
			try {
				dataProvider.getSignalCollection().insertOne(document);
			} catch (Exception e) {
				logger.error(marker, "Exception inserting signal mother fucker " + e.toString());
			}
		} catch (Exception e) {
			logger.error(marker, "Exception converting Entity signal to Mongo Document " + e.toString());
		}
	}

	@Override
	public List<StreamEntitySignal> signalDump(int limit) throws Exception {
		try {
			List<StreamEntitySignal> signals = new ArrayList<StreamEntitySignal>();
			MongoCursor<Document> documents = dataProvider.getSignalCollection().find().batchSize(5).cursor();
			while (documents.hasNext()) {
				StreamEntitySignal signal = MongoStreamConverter.documentToSignal(documents.next());
				signals.add(signal);
			}
			documents.close();
			return signals;
		} catch (Exception e) {
			logger.error(marker, "Exception signal dump" + e.toString());
			throw e;
		}

	}

	@Override
	public StreamDataProvider getDataProvider() {
		return this.dataProvider;
	}

	@Override
	public StreamSignalIngestor getSignalIngestor() {
		return signalIngestor;
	}

	@Override
	public StreamSessionSignals getSessionSignals() {
		return sessionSignals;
	}

	//SD21-GIFT-07 overried the new method this is where fun starts 
	@Override
	public SignalSearchResponse signalSearch(SignalSearchRequest request) throws Exception {
		SignalSearchResponse signalSearchResponse = new SignalSearchResponse();
		//SD21-GIFT-08 - i'll start walking through the code/psuedo code. 
		
		//we want to time this search request use DStopWatchTimer and start it
		DStopWatch timer = DStopWatch.create();
		timer.start();
				
		//SD21-GIFT-09 unwrap Dunkware time zone that serializes to ZoneId for java 
		DTimeZone timeZone = getStreamDescriptor().getTimeZone(); 
		ZoneId zoneId = DTimeZone.toZoneId(timeZone);

		Bson query = MongoStreamConverter.signalSearchToMongoQuery(request.getSignalTypes(), request.getEntities(), request.getSearchRangeStart(), request.getSearchRangeStop(), descriptor);

		try {
			List<StreamEntitySignal> signals = new ArrayList<>();
			MongoCursor<Document> documents = dataProvider.getSignalCollection().find(query).limit(request.getLimit()).batchSize(5).cursor();
			while (documents.hasNext()) {
				StreamEntitySignal signal = MongoStreamConverter.documentToSignal(documents.next());
				signals.add(signal);
			}
			documents.close();

			timer.stop();
			double completedSeconds = timer.getCompletedSeconds();

			signalSearchResponse.setQueryTime(completedSeconds);
			signalSearchResponse.setResults(signals);
			return signalSearchResponse;
		} catch (Exception e) {
			logger.error(marker, "Exception signal dump" + e.toString());
			throw e;
		}

	}

	//SD-33-04 - implementation calls mongo converter
	@Override
	public EntitySignalCountResponse entitySignalCountSearchRequest(EntitySignalCountRequest request)
			throws Exception {
		EntitySignalCountResponse entitySignalCountResponse = new EntitySignalCountResponse();

		DStopWatch timer = DStopWatch.create();
		timer.start();

		List<Bson> aggregations = MongoStreamConverter.signalCountToMongoQuery(request.getEntityId(), request.getSignalTypes(), request.getSearchRangeStart(), request.getSearchRangeStop(), descriptor);

		try {
			List<EntitySignalCount> signalCounts = new ArrayList<>();
			MongoCursor<Document> documents = dataProvider.getSignalCollection().aggregate(aggregations).cursor();
			while (documents.hasNext()) {
				EntitySignalCount signalCount = MongoStreamConverter.documentToSignalCount(documents.next());
				signalCounts.add(signalCount);
			}
			documents.close();

			timer.stop();
			double completedSeconds = timer.getCompletedSeconds();

			entitySignalCountResponse.setEntityId(request.getEntityId());
			entitySignalCountResponse.setSearchTime(completedSeconds);
			entitySignalCountResponse.setSignalCounts(signalCounts);
			return entitySignalCountResponse;
		} catch (Exception e) {
			logger.error(marker, "Exception signal count dump" + e.toString());
			throw e;
		}

	}

	

}
