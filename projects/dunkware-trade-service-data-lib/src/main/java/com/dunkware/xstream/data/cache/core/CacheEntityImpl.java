package com.dunkware.xstream.data.cache.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import com.dunkware.xstream.data.cache.CacheEntity;
import com.dunkware.xstream.data.cache.CacheEntityListener;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheEntitySnapshot;
import com.dunkware.xstream.data.cache.CacheStream;

public class CacheEntityImpl implements CacheEntity  {
	
	private Map<LocalDateTime,CacheEntitySnapshot> snapshots = new ConcurrentHashMap<LocalDateTime,CacheEntitySnapshot>();
	private Vector<CacheEntitySignal> signals = new Vector<CacheEntitySignal>();
	private Semaphore signalLock = new Semaphore(1);
	private Vector<CacheEntityListener> listeners = new Vector<CacheEntityListener>();
	private Semaphore listenerLock = new Semaphore(1);
	
	private int id; 
	private String identifier; 
	
	private CacheEntitySnapshot lastSnapshot;
	private CacheStream stream; 
	
	private boolean snapshotHistory; 
	
	public void init(CacheStream stream, int id, String identifier) { 
		this.id = id;
		this.identifier = identifier;
		this.stream = stream; 
		this.snapshotHistory = stream.getInput().isSnapshotHistory();
	}

	@Override
	public Map<LocalDateTime, CacheEntitySnapshot> getSnapshots() {
		return snapshots;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public CacheEntitySnapshot getLastSnapshot() {
		return lastSnapshot;
	}
	

	@Override
	public CacheStream getStream() {
		return stream;
	}

	@Override
	public int getSnapshotCount() {
		return snapshots.values().size();
	}

	@Override
	public List<CacheEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop) {
		try {
			List<CacheEntitySignal> results = new ArrayList<CacheEntitySignal>();
			signalLock.acquire();
			for (CacheEntitySignal cacheEntitySignal : signals) {
				if(cacheEntitySignal.getTime().isAfter(start) == true && cacheEntitySignal.getTime().isAfter(stop) == false) { 
					results.add(cacheEntitySignal);
				}
			}
			return results;
		} catch (Exception e) {
			//TOOD: log
			return new ArrayList<CacheEntitySignal>();
		} finally {
			signalLock.release();
		}
	}

	@Override
	public List<CacheEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes) {
		try {
			List<CacheEntitySignal> results = new ArrayList<CacheEntitySignal>();
			signalLock.acquire();
			for (CacheEntitySignal cacheEntitySignal : signals) {
				boolean ofType = false;
				for (String type : signalTypes) {
					if(cacheEntitySignal.getIdentifier().equals(type)) { 
						ofType = true;
						break;
					}
				}
				if(!ofType) { 
					continue;
				}
				
				if(cacheEntitySignal.getTime().isAfter(start) == true && cacheEntitySignal.getTime().isAfter(stop) == false) { 
					results.add(cacheEntitySignal);
				}
			}
			return results;
		} catch (Exception e) {
			//TOOD: log
			return new ArrayList<CacheEntitySignal>();
		} finally {
			signalLock.release();
		}
	}

	@Override
	public void addSnapshot(CacheEntitySnapshot snapshot) {
		if(snapshotHistory) { 
			this.snapshots.put(snapshot.getTime(), snapshot);		
		}
		this.lastSnapshot = snapshot;
		// notify listeners. 
		
	}

	@Override
	public void addListener(CacheEntityListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		}	 finally { 
			listenerLock.release();
		}
	}

	@Override
	public void removeListener(CacheEntityListener listener) {
		try {
			listenerLock.acquire();
			listeners.add(listener);
		} catch (Exception e) {
			// TODO: handle exception
		}	 finally { 
			listenerLock.release();
		}
		
	}

	

	@Override
	public Vector<CacheEntitySignal> getSignals() {
		return signals;
	}

	
	@Override
	public int getSignalCount(LocalDateTime from, LocalDateTime to, String... types) {
		try {
			signalLock.acquire();
			int counter = 0; 
			for (CacheEntitySignal cacheEntitySignal : signals) {
				boolean ofType = false;
				for (String type : types) {
					if(cacheEntitySignal.getIdentifier().equals(type)) { 
						ofType = true;
						break;
					}
				}
				if(!ofType) { 
					continue;
				}
				
				if(cacheEntitySignal.getTime().isAfter(from) == true && cacheEntitySignal.getTime().isAfter(to) == false) { 
					counter++;
				}
			}
			return counter;
		} catch (Exception e) {
			return 0;
		} finally {
			signalLock.release();
		}
		
	}

	

	@Override
	public void insertSignal(CacheEntitySignal signal) {
		// Notify Entity Signal 
		try {
			signalLock.acquire();
			signals.add(signal);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			signalLock.release();
		}
	}
	
	

	

	
	
	

}
