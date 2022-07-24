package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.xstream.container.ContainerExtType;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntityQuery;
import com.dunkware.xstream.net.core.container.ContainerEntityScanner;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerEntityVar;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerExtension;
import com.dunkware.xstream.net.core.container.ContainerInput;
import com.dunkware.xstream.net.core.container.ContainerListener;
import com.dunkware.xstream.net.core.container.ContainerRegistry;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;

public class ContainerImpl implements Container {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, ContainerEntityVar> vars = new ConcurrentHashMap<String, ContainerEntityVar>();

	private List<ContainerEntity> entities = new ArrayList<ContainerEntity>();
	private Map<String, Integer> entityIndex = new ConcurrentHashMap<String, Integer>();
	private List<ContainerEntitySignal> signals = new ArrayList<ContainerEntitySignal>();
	private Semaphore signalLock = new Semaphore(1);

	private List<ContainerListener> listeners = new ArrayList<ContainerListener>();
	private Semaphore listenerLock = new Semaphore(1);
	private Map<ContainerExtType, ContainerExtension> extensions = new ConcurrentHashMap<ContainerExtType, ContainerExtension>();

	private ContainerInput input;

	private volatile LocalDateTime time;

	ContainerRegistry registry;

	private LocalDateTime startTime;

	private ZoneId zoneId; 
	private ZoneOffset zoneOffset; 
	
	private AtomicLong entitySnapshotCount = new AtomicLong(0);
	private AtomicLong entitySignalCount = new AtomicLong(0);

	private DebugThread debugThread;

	private Marker predicateSearchMarker = MarkerFactory.getMarker("EntityPredicateSearch");
	
	@Override
	public void start(ContainerInput input) throws ContainerException {
		if (logger.isInfoEnabled()) {
			logger.info("Starting Stream Container " + input.getIdentifier());
		}
		entities = Collections.synchronizedList(entities);
		signals = Collections.synchronizedList(signals);
		startTime = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
		this.input = input;
		this.zoneId = DTimeZone.toZoneId(input.getTimeZone());
		zoneOffset = DunkTime.zoneOffset(zoneId);
		
		registry = ContainerRegistry.get();
		for (ContainerExtType type : input.getExtensions()) {

			ContainerExtension ext = registry.create(type);
			if (logger.isDebugEnabled()) {
				logger.debug("Starting Container Extension " + ext.getClass().getName() + " on container "
						+ input.getIdentifier());
			}
			ext.init(this, type);
			extensions.put(type, ext);
		}

		for (ContainerExtension stashExtension : extensions.values()) {
			stashExtension.containerStarting(this);
		}

		for (ContainerExtension stashExtension : extensions.values()) {
			stashExtension.containerStarted(this);
		}

		debugThread = new DebugThread();
		debugThread.start();

	}

	@Override
	public ContainerInput getInput() {
		return input;
	}

	@Override
	public void dispsoe() {
		for (ContainerExtension stashExtension : extensions.values()) {
			stashExtension.containerDisposed(this);
		}
		debugThread.interrupt();

	}

	@Override
	public LocalDateTime getStartTime() {
		return startTime;
	}

	@Override
	public boolean hasEntity(String identifier) {
		return entityIndex.get(identifier) == null ? false : true;
	}

	@Override
	public void dispose() {
		newSession();
	}

	@Override
	public void addListener(ContainerListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.add(listener);
				} catch (Exception e) {
				} finally {
					listenerLock.release();

				}
			}
		};

		getExecutor().execute(runnable);
	}

	@Override
	public void removeListener(ContainerListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					listenerLock.acquire();
					listeners.remove(listener);
				} catch (Exception e) {
				} finally {
					listenerLock.release();

				}
			}
		};

		getExecutor().execute(runnable);
	}

	@Override
	public ContainerEntity getEntity(String identifier) throws ContainerException {
		Integer index = entityIndex.get(identifier);
		if (index == null) {
			throw new ContainerException("Getting an entity does not exist in index " + identifier);
		}
		return entities.get(index - 1);

	}

	@Override
	public LocalDateTime getDateTime() {
		if (time == null) {
			time = LocalDateTime.now(DTimeZone.toZoneId(input.getTimeZone()));
		}
		return time;
	}

	@Override
	public void newSession() {
		entities.clear();
		entityIndex.clear();
		signals.clear();
		startTime = LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));

	}

	@Override
	public ContainerEntityQuery entityQuery(SessionEntitySearch search) throws ContainerSearchException {
		ContainerEntityQuery query = new ContainerEntityQueryImpl();
		query.init(this, search);
		return query;
	}

	@Override
	public ContainerEntityScanner entityScanner(SessionEntityScanner scanner) throws ContainerSearchException {
		ContainerEntityScannerImpl containerScanner = new ContainerEntityScannerImpl(); 
		containerScanner.init(this, scanner);
		return containerScanner;

	}
	

	@Override
	public ContainerSearchResults<ContainerEntity> entitySearch(List<Predicate<ContainerEntity>> predicates)
			throws ContainerSearchException {
		ContainerSearchResults<ContainerEntity> results = new ContainerSearchResults<ContainerEntity>();
		for (ContainerEntity entity : entities) {
			for (Predicate<ContainerEntity> predicate : predicates) {
				if(!predicate.test(entity)) { 
					continue;
				}
				results.getResults().add(entity);
			}
		}
		if(logger.isDebugEnabled()) { 
			logger.debug(predicateSearchMarker, "Entity Search Returned {} Results", results.getResults().size());
		}
		return results;
	}

	@Override
	public boolean hasExtension(Class extClass) {
		// for each key
		return false;
	}

	@Override
	public ContainerExtension getExtension(Class extClass) throws ContainerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSnapshotCount() {
		return (int) entitySnapshotCount.get();
	}

	@Override
	public DTimeZone getTimeZone() {
		return input.getTimeZone();
	}
	
	
	

	@Override
	public ZoneOffset getZoneOffset() {
		return zoneOffset;
	}

	@Override
	public ZoneId getZoneId() {
		return zoneId;
	}

	@Override
	public List<ContainerEntitySignal> getSignals() {
		return this.signals;
	}

	@Override
	public void consumeStreamSnapshot(GEntitySnapshot snapshot) {
		ContainerEntity entity = getOrCreateEntity(snapshot.getId(), snapshot.getIdentifier());
		try {
			entity.consumeSnapshot(snapshot);
		} catch (Exception e) {
			logger.error("Exception creating Container Entity Snapshot " + e.toString());
		}
	}

	@Override
	public void consumeStreamSignal(GEntitySignal signal) {
		try {
			ContainerEntitySignal sig = ContainerHelper.createSignal(signal, this);
			try {
				this.signalLock.acquire();
				signals.add(sig);
				ContainerEntity ent = getEntity(sig.getEntityIdent());
				if (ent != null) {
					ent.consumeSignal(signal);
				}
			} catch (Exception e) {
				logger.error("signal lock aquire exception" + e.toString());
			} finally {
				this.signalLock.release();
			}
		} catch (Exception e) {
			logger.error("Eception creating container entity signal " + e.toString());
		}

	}

	@Override
	public void consumeStreamTime(GStreamTimeUpdate update) {
		try {
			LocalDateTime dt = ContainerHelper.convertTimestamp(update.getTime(), getTimeZone());
			this.time = dt;
		} catch(Exception e) { 
			logger.error("Container " + input.getIdentifier() + " consume time stamp exception " + e.toString());
		}

		// notify time listeners
		try {
			listenerLock.acquire();
			for (ContainerListener list : listeners) {
				try {
					list.timeUpdate(this);					
				} catch (Exception e) {
					logger.error("Time Listener class " + list.getClass().getName() + " Exception " + e.toString());
				}

			}
		} catch (Exception e) {
		
		} finally { 
			listenerLock.release();
		}
	}

	@Override
	public DExecutor getExecutor() {
		return input.getExecutor();
	}

	@Override
	public List<ContainerEntity> getEntities() {
		return entities;
	}

	@Override
	public boolean storeSnapshots() {
		return input.isSnapshotHistory();
	}

	private synchronized ContainerEntity getOrCreateEntity(int id, String ident) {
		if (entityIndex.get(ident) == null) {
			ContainerEntityImpl entity = new ContainerEntityImpl();
			entity.init(this, id, ident);
			int size = entities.size();
			entities.add(entity);
			entityIndex.put(ident, Integer.valueOf(size + 1));
			return entity;
		} else {
			return entities.get(entityIndex.get(ident) - 1);
		}

	}

	private class DebugThread extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					Thread.sleep(50000);
					logger.debug("Container: " + input.getIdentifier() + entitySignalCount.get() + " Signals "
							+ entitySnapshotCount.get() + " Snapshots and " + entities.size() + " entities");
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
