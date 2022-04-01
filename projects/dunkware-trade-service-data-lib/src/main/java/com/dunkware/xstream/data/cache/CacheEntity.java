package com.dunkware.xstream.data.cache;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface CacheEntity {
	
	 Map<LocalDateTime,CacheEntitySnapshot> getSnapshots();
	 
	 Vector<CacheEntitySignal> getSignals();
	 
	 String getIdentifier();
	 
	 int getId();
	 
	 CacheEntitySnapshot getLastSnapshot();
	 
	 public int getSnapshotCount();
	 	 
	 public void addSnapshot(CacheEntitySnapshot snapshot); 
	 
	 public void addListener(CacheEntityListener listener);
	 
	 public void removeListener(CacheEntityListener listener);
	 
	 List<CacheEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop);
	 
	 List<CacheEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes);
	 
	 int getSignalCount(LocalDateTime from, LocalDateTime to, String... types);
	 
	 void insertSignal(CacheEntitySignal signal);
	 
	 public CacheStream getStream();

}
