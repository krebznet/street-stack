package com.dunkware.xstream.data.cache.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.xstream.data.cache.CacheEntity;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheException;
import com.dunkware.xstream.data.cache.CacheExtension;
import com.dunkware.xstream.data.cache.CacheExtensionType;
import com.dunkware.xstream.data.cache.CacheRegistry;
import com.dunkware.xstream.data.cache.CacheSearchResults;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheStreamInput;
import com.dunkware.xstream.data.cache.CacheStreamObserver;
import com.dunkware.xstream.data.cache.CacheValueSet;
import com.dunkware.xstream.data.cache.StashStreamListener;
import com.dunkware.xstream.data.cache.search.SignalSearchBuilder;

public class CacheStreamImpl implements CacheStream {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, CacheEntity> entities = new ConcurrentHashMap<String, CacheEntity>();
	private List<CacheEntitySignal> signals = new ArrayList<CacheEntitySignal>();
	private Semaphore signalLock = new Semaphore(1);
	private List<StashStreamListener> listeners = new ArrayList<StashStreamListener>();
	private Semaphore listtenerLock = new Semaphore(1);
	private List<CacheExtension> extensions = new ArrayList<CacheExtension>();
	
	private CacheStreamInput input;

	CacheRegistry registry;

	private AtomicLong entitySnapshotCount = new AtomicLong(0);
	private AtomicLong entitySignalCount = new AtomicLong(0);

	private DebugThread debugThread;

	@Override
	public void start(CacheStreamInput input) throws CacheException {
		this.input = input;
		registry = CacheRegistry.get();
		for (CacheExtensionType type : input.getExtensions()) {
			CacheExtension ext = registry.crateExtension(type);
			ext.init(this, type);
			extensions.add(ext);
		}

		for (CacheExtension stashExtension : extensions) {
			stashExtension.stashStarting(this);
		}

		for (CacheExtension stashExtension : extensions) {
			stashExtension.stashStarted(this);
		}

		debugThread = new DebugThread();
		debugThread.start();

	}

	@Override
	public CacheStreamInput getInput() {
		return input;
	}

	@Override
	public void dispsoe() {
		for (CacheExtension stashExtension : extensions) {
			stashExtension.stashDisposed(this);
		}
		debugThread.interrupt();

	}

	@Override
	public Map<String, CacheEntity> getEntities() {
		return entities;
	}

	@Override
	public boolean hasEntity(String identifier) {
		return entities.containsKey(identifier);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	

	@Override
	public List<CacheEntitySignal> entitySignals(CacheEntity entity, GTimeUnit timeUnit, int timeValue, String signalType) {
		List<Predicate<CacheEntitySignal>> predicates =  SignalSearchBuilder.newBuilder().
				relativeTimeRange(timeUnit,timeValue).includeEntity(entity.getIdentifier()).includeSignalType(signalType).build();
		for (Predicate<CacheEntitySignal> predicate : predicates) {
			if (predicate instanceof CacheStreamObserver) { 
				CacheStreamObserver obs = (CacheStreamObserver)predicate;
				obs.update(this);
			}
		}
		CacheSearchResults<CacheEntitySignal> results = signalSearch(predicates);
		return results.getResults();
		
		
		
	}

	@Override
	public CacheEntity getEntity(String identifier) throws CacheException {
		CacheEntity ent = entities.get(identifier);
		if (ent == null) {
			throw new CacheException("Entity " + identifier + " not found in stash");
		}
		return ent;
	}

	

	@Override
	public LocalDateTime getTime() {
		return LocalDateTime.now(DTimeZone.toZoneId(input.getTimeZone()));
	}
	

	@Override
	public List<CacheEntitySignal> getSignals() {
		try {
			signalLock.acquire();
			return signals;
		} catch (Exception e) {
			return null;
		} finally { 
			signalLock.release();
		}
	}

	@Override
	public CacheSearchResults<CacheEntity> entitySearch(List<Predicate<CacheEntity>> predicates) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public CacheSearchResults<CacheEntitySignal> signalSearch(List<Predicate<CacheEntitySignal>> predicates) {
		for (Predicate<CacheEntitySignal> signalPredicate : predicates) {
			if (signalPredicate instanceof CacheStreamObserver) { 
				CacheStreamObserver obs = (CacheStreamObserver)signalPredicate;
				obs.update(this);
				
			}
		}
		CacheSearchResults<CacheEntitySignal> results = new CacheSearchResults<CacheEntitySignal>();
		DStopWatch watch = DStopWatch.create();
		
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug("Starting Signal Search With Signal Count of " + signals.size() + " predicate count " + predicates.size());;
			}
			signalLock.acquire();
			watch.start();
			Predicate<CacheEntitySignal> composite = predicates.stream()
			        .reduce(x -> true, Predicate::and);
			List<CacheEntitySignal> goods = signals.parallelStream().filter(composite).collect(Collectors.toList());
			watch.stop();
			if(logger.isDebugEnabled()) { 
				logger.debug("Finished signal search in " + watch.getCompletedSeconds() + " with result size of " + goods.size());
			}
			results.setData(goods, watch.getCompletedSeconds());
			return results;
		} catch (Exception e) {
			results.setError("thrown exception " + e.toString());
			return results;
		} finally {
			signalLock.release();
		}
		
	}
	
	

	
	@Override
	public int getSnapshotCount() {
		return (int)entitySnapshotCount.get();
	}

	@Override
	public void insertSnapshot(int entityId, String entityIdentifier, LocalDateTime time, CacheValueSet vars) {
		CacheEntity ent = getOrCreateEntity(entityId, entityIdentifier);
		CacheEntitySnapshotImpl snapshot = new CacheEntitySnapshotImpl(time, vars);
		ent.addSnapshot(snapshot);
		entitySnapshotCount.incrementAndGet();
	}

	@Override
	public void insertSignal(int signalId, String signalIdentifier, int entityId, String entityIdentifier,
			LocalDateTime time, CacheValueSet vars) {
		CacheEntitySignalImpl sig = new CacheEntitySignalImpl(this, signalId, signalIdentifier, entityId,
				entityIdentifier, time, signalIdentifier, vars);
		try {
			signalLock.acquire();
			signals.add(sig);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			signalLock.release();
		}
		
		entitySignalCount.incrementAndGet();
	}

	@Override
	public boolean storeSnapshots() {
		return input.isSnapshotHistory();
	}

	private CacheEntity getOrCreateEntity(int id, String ident) {
		CacheEntity ent = entities.get(ident);
		if (ent == null) {
			CacheEntityImpl entity = new CacheEntityImpl();
			entity.init(this, id, ident);
			;
			entities.put(ident, entity);
		}
		return entities.get(ident);
	}

	private class DebugThread extends Thread {

		public void run() {
			try {
				while (!interrupted()) {
					Thread.sleep(50000);
					logger.debug("Stash: " + input.getIdentifier() + entitySignalCount.get() + " Signals "
							+ entitySnapshotCount.get() + " Snapshots and " + entities.size() + " entities");
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
