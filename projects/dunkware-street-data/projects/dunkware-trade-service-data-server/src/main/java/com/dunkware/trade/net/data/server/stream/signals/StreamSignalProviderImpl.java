package com.dunkware.trade.net.data.server.stream.signals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.anot.ADEventMethod;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelClient;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintChannelException;
import com.dunkware.stream.cluster.proto.controller.messages.StreamSessionStarting;
import com.dunkware.trade.net.data.server.stream.converters.MongoStreamConverter;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalGrid;
import com.dunkware.trade.net.data.server.stream.signals.beangrids.StreamSignalTypeStatsGrid;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalList;
import com.dunkware.trade.net.data.server.stream.signals.beanlists.StreamSignalTypeStatsList;
import com.dunkware.trade.net.data.server.stream.streamprovider.StreamDataProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.mongodb.client.MongoCursor;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;

public class StreamSignalProviderImpl implements StreamSignalProvider, StreamSignalIngestorListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalProvider");

	@Autowired
	private ApplicationContext ac;

	private StreamSignalIngestor signalIngestor;

	private StreamDataProvider dataProvider;
	private StreamDescriptor descriptor;

	@Autowired
	private ExecutorService executor;

	@Autowired
	private DunkNet dunkNet;

	private EventList<StreamSignalBean> sessionSignals = GlazedLists.eventList(new ArrayList<StreamSignalBean>());

	public void init(StreamDataProvider dataProvider) throws Exception {
		try {
			dunkNet.extension(this);
		} catch (Exception e) {
			logger.error(marker, "Exception adding extensions on DunkNet " + e.toString());
		}

		logger.info(marker, "StreamSignals Impl");
		this.dataProvider = dataProvider;
		this.descriptor = dataProvider.getDescriptor();
		logger.info(marker, "Starting {} Stream Signals ", descriptor.getIdentifier());
		try {
			signalIngestor = new StreamSignalIngestor();
			signalIngestor.start(this);
			signalIngestor.addSignalListener(this);;
		} catch (Exception e) {
			logger.error(marker, "Exception starting stream signal ingjestor " + e.toString());
			throw new Exception("cannot start signal ingestor " + e.toString());
		}
		

	}

	@Override
	public StreamBlueprintChannelClient streamBlueprint() throws StreamBlueprintChannelException {
		return dataProvider.getBlueprint();
	}

	@Override
	public String streamIdentifier() {
		return descriptor.getIdentifier();
	}

	@Override
	public StreamDescriptor streamDescriptor() {
		return descriptor;
	}

	public DExecutor getExecutor() {
		return executor.get();
	}

	@Override
	public StreamDataProvider dataProvider() {
		return this.dataProvider;
	}

	@Override
	public StreamSignalIngestor getSignalIngestor() {
		return signalIngestor;
	}

	@Override
	public List<StreamSignalBean> signalDataQuery(StreamSignalQuery dataQuery) throws Exception {
		Bson query = MongoStreamConverter.signalSearchToMongoQuery(dataQuery.getSignalTypes(), dataQuery.getEntities(),
				dataQuery.getTimeRangeStart(), dataQuery.getTimeRangeEnd(), streamDescriptor());
		List<StreamSignalBean> signals = new ArrayList<>();
		try {
			DStopWatch timer = DStopWatch.create();
			timer.start();
			MongoCursor<Document> documents = dataProvider.getSignalCollection().find(query).limit(dataQuery.getLimit())
					.batchSize(5).cursor();
			while (documents.hasNext()) {
				StreamEntitySignal signal = MongoStreamConverter.documentToSignal(documents.next());
				signals.add(StreamSignalHelper.entitySignalToBean(signal, streamBlueprint()));
			}
			documents.close();
			timer.stop();
			double completedSeconds = timer.getCompletedSeconds();
			if (logger.isDebugEnabled()) {
				logger.debug(marker, "Stream Data Query Completed in {} seconds " + completedSeconds);
			}

		} catch (Exception e) {
			logger.error(marker, "Exception signal dump" + e.toString());
			throw e;
		}
		return signals;

	}

	@Override
	public LocalDate streamDate() {
		ZoneId zoneId = DTimeZone.toZoneId(streamDescriptor().getTimeZone());
		return LocalDate.now(zoneId);

	}

	@Override
	public StreamSignalTypeStatsList signalTypeStatsList(StreamSignalTypeStatsQuery query) throws Exception {
		StreamSignalTypeStatsList list = new StreamSignalTypeStatsList();
		list.start(query, this);
		;
		return list;
	}

	
	@Override
	public StreamSignalList signalList(StreamSignalQuery query) throws Exception {
		StreamSignalList list = new StreamSignalList();
		list.start(query, this);
		return list;
	}

	@Override
	public EventList<StreamSignalBean> sessionSignals() {
		return sessionSignals;
	}

	/**
	 * Returns the session signal query
	 * 
	 * @param predicate
	 * @return
	 */
	public List<StreamSignalBean> sessionSignalQuery(Predicate<StreamSignalBean> predicate) {
		try {
			sessionSignals.getReadWriteLock().readLock().lock();
			return sessionSignals.stream().filter(predicate).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(marker, "Exception executing stream signal session query " + e.toString());
			return new ArrayList<StreamSignalBean>();
		} finally {
			sessionSignals.getReadWriteLock().readLock().unlock();
		}

	}

	@Override
	public void onSignal(StreamEntitySignal signal) {
		StreamSignalBean bean = null;
		try {
			bean = StreamSignalHelper.entitySignalToBean(signal, streamBlueprint());
		} catch (Exception e) {
			logger.error(marker, "Exception converting EntitySignal to stream signal " + e.toString());
			return;
		}
		try {
			sessionSignals.getReadWriteLock().writeLock().lock();
			sessionSignals.add(bean);
		} catch (Exception e) {
			logger.error(marker, "WTF locking in on signal " + e.toString());
		} finally {
			sessionSignals.getReadWriteLock().writeLock().unlock();
		}
	}

	@ADEventMethod()
	public void onSessionStarting(StreamSessionStarting sessionStaring) {
		if(sessionStaring.getIdentifier().equals(descriptor.getIdentifier()) == false) { 
			return;
		}
		logger.info(marker, "OnStreamSession Starting clearing session signals");
		Thread runner = new Thread() {

			public void run() {
				try {
					sessionSignals.getReadWriteLock().writeLock().lock();
					sessionSignals.clear();

				} catch (Exception e) {
					logger.error(marker, "Exception clearing session signals on session starting");
				} finally {
					sessionSignals.getReadWriteLock().writeLock().unlock();
				}
			}

		};
		
		runner.start();

	}

}
