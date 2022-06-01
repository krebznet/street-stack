package com.dunkware.xstream.net.core.container.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.net.proto.netstream.GNetEntityMatcher;
import com.dunkware.net.proto.netstream.GNetEntityScannerRequest;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerEntityVar;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerExtension;
import com.dunkware.xstream.net.core.container.ContainerExtensionType;
import com.dunkware.xstream.net.core.container.ContainerInput;
import com.dunkware.xstream.net.core.container.ContainerObserver;
import com.dunkware.xstream.net.core.container.ContainerRegistry;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;
import com.dunkware.xstream.net.core.container.ContainerTimeListener;
import com.dunkware.xstream.net.core.container.search.ContainerEntityMatcherPredicateBuilder;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;
import com.dunkware.xstream.net.core.scanner.StreamEntityScanner;

public class ContainerImpl implements Container {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String,ContainerEntityVar> vars = new ConcurrentHashMap<String,ContainerEntityVar>();
	
	private List<ContainerEntity> entities = new ArrayList<ContainerEntity>();
	private Map<String,Integer> entityIndex = new ConcurrentHashMap<String,Integer>();
	private List<ContainerEntitySignal> signals = new ArrayList<ContainerEntitySignal>();
	private Semaphore signalLock = new Semaphore(1);
	
	private List<ContainerTimeListener> timeListeners = new ArrayList<ContainerTimeListener>();
	private Semaphore timeListenerLock = new Semaphore(1);
	private List<ContainerExtension> extensions = new ArrayList<ContainerExtension>();
	
	
	private ContainerInput input;

	private volatile LocalDateTime time; 
	
	ContainerRegistry registry;

	private AtomicLong entitySnapshotCount = new AtomicLong(0);
	private AtomicLong entitySignalCount = new AtomicLong(0);

	private DebugThread debugThread;

	@Override
	public void start(ContainerInput input) throws ContainerException {
		entities = Collections.synchronizedList(entities);
		signals = Collections.synchronizedList(signals);

		this.input = input;
		registry = ContainerRegistry.get();
		for (ContainerExtensionType type : input.getExtensions()) {
			ContainerExtension ext = registry.crateExtension(type);
			ext.init(this, type);
			extensions.add(ext);
		}

		for (ContainerExtension stashExtension : extensions) {
			stashExtension.containerStarting(this);
		}

		for (ContainerExtension stashExtension : extensions) {
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
		for (ContainerExtension stashExtension : extensions) {
			stashExtension.containerDisposed(this);
		}
		debugThread.interrupt();

	}

	

	@Override
	public boolean hasEntity(String identifier) {
		return entityIndex.get(identifier) == null ? false : true;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	
	@Override
	public void addTimeListener(ContainerTimeListener listener) {
		try {
			timeListenerLock.acquire();
			timeListeners.add(listener);
		} catch (Exception e) {
			logger.error("Exception adding time listener " + e.toString());
		} finally { 
			timeListenerLock.release();
		}
	}

	@Override
	public void removeTimeListener(ContainerTimeListener listener) {
		try {
			timeListenerLock.acquire();
			timeListeners.add(listener);
		} catch (Exception e) {
			logger.error("Exception adding time listener " + e.toString());
		} finally { 
			timeListenerLock.release();
		}
	}

	@Override
	public List<ContainerEntitySignal> entitySignals(ContainerEntity entity, GTimeUnit timeUnit, int timeValue, String signalType) {
		/*
		 * List<Predicate<ContainerEntitySignal>> predicates =
		 * ContainerSignalSearchBuilder.newBuilder().
		 * relativeTimeRange(timeUnit,timeValue).includeEntity(entity.getIdent()).
		 * includeSignalType(signalType).build(); for (Predicate<ContainerEntitySignal>
		 * predicate : predicates) { if (predicate instanceof ContainerObserver) {
		 * ContainerObserver obs = (ContainerObserver)predicate; obs.update(this); } }
		 * ContainerSearchResults<ContainerEntitySignal> results =
		 * signalSearch(predicates); return results.getResults();
		 */
		return new ArrayList<ContainerEntitySignal>();
	}

	@Override
	public ContainerEntity getEntity(String identifier) throws ContainerException {
		Integer index = entityIndex.get(identifier);
		if(index == null) { 
			throw new ContainerException("Getting an entity does not exist in index " + identifier);
		}
		return entities.get(index -1);
		
	}

	

	@Override
	public LocalDateTime getTime() {
		if(time == null) { 
			time = LocalDateTime.now(DTimeZone.toZoneId(input.getTimeZone()));
		}
		return time;
	}
	


	@Override
	public void deleteContainer() {
		entities.clear();
		entityIndex.clear();
		signals.clear();
		
	}

	
	@Override
	public ContainerSearchResults<ContainerEntity> entitySearch(GNetEntityMatcher matcher)
			throws ContainerException {
		List<Predicate<ContainerEntity>> predicates = null;
		try {
			predicates = ContainerEntityMatcherPredicateBuilder.build(matcher, this);	
		} catch (Exception e) {
			throw new ContainerException("Exception building GEntityMatrcher predicates " + e.toString());
		}
		return entitySearch(predicates);

	}



	@Override
	public ContainerSearchResults<ContainerEntity> entitySearch(List<Predicate<ContainerEntity>> predicates ) {
		for (Predicate<ContainerEntity> signalPredicate : predicates) {
			if (signalPredicate instanceof ContainerObserver) { 
				ContainerObserver obs = (ContainerObserver)signalPredicate;
				obs.update(this);
				
			}
		}
		ContainerSearchResults<ContainerEntity> results = new ContainerSearchResults<ContainerEntity>();
		DStopWatch watch = DStopWatch.create();
		
		try {
			if(logger.isDebugEnabled()) { 
				logger.debug("Starting container entity Search With Signal Count of " + signals.size() + " predicate count " + predicates.size());;
			}
			
			watch.start();
			Predicate<ContainerEntity> composite = predicates.stream()
			        .reduce(x -> true, Predicate::and);
			List<ContainerEntity> goods = entities.parallelStream().filter(composite).collect(Collectors.toList());
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
	
	
	/*
	 * @Override public ContainerSearchResults<ContainerEntitySignal>
	 * signalSearch(List<Predicate<ContainerEntitySignal>> predicates) { for
	 * (Predicate<ContainerEntitySignal> signalPredicate : predicates) { if
	 * (signalPredicate instanceof ContainerObserver) { ContainerObserver obs =
	 * (ContainerObserver)signalPredicate; obs.update(this);
	 * 
	 * } } ContainerSearchResults<ContainerEntitySignal> results = new
	 * ContainerSearchResults<ContainerEntitySignal>(); DStopWatch watch =
	 * DStopWatch.create();
	 * 
	 * try { if(logger.isDebugEnabled()) {
	 * logger.debug("Starting Signal Search With Signal Count of " + signals.size()
	 * + " predicate count " + predicates.size());; } signalLock.acquire();
	 * watch.start(); Predicate<ContainerEntitySignal> composite =
	 * predicates.stream() .reduce(x -> true, Predicate::and);
	 * List<ContainerEntitySignal> goods =
	 * signals.parallelStream().filter(composite).collect(Collectors.toList());
	 * watch.stop(); if(logger.isDebugEnabled()) {
	 * logger.debug("Finished signal search in " + watch.getCompletedSeconds() +
	 * " with result size of " + goods.size()); } results.setData(goods,
	 * watch.getCompletedSeconds()); return results; } catch (Exception e) {
	 * results.setError("thrown exception " + e.toString()); return results; }
	 * finally { signalLock.release(); }
	 * 
	 * }
	 */
	
	

	
	@Override
	public int getSnapshotCount() {
		return (int)entitySnapshotCount.get();
	}
	
	

	
	@Override
	public DTimeZone getTimeZone() {
		return input.getTimeZone();
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
				if(ent != null) { 
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
			try {
				timeListenerLock.acquire();
				for (ContainerTimeListener listener : timeListeners) {
					listener.timeUpdate(this);
				}
			} catch (Exception e) {
				logger.error("Exception invoking time listeners " + e.toString());
			} finally { 
				timeListenerLock.release();
			}
		} catch (Exception e) {
			logger.error("Exception converting timestamp on update " + e.toString());
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
		if(entityIndex.get(ident) == null) { 
			ContainerEntityImpl entity = new ContainerEntityImpl();
			entity.init(this, id, ident);
			int size = entities.size();
			entities.add(entity);
			entityIndex.put(ident,Integer.valueOf(size + 1));
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
