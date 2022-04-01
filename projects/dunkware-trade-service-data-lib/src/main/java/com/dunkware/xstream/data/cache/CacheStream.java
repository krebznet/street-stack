package com.dunkware.xstream.data.cache;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.dunkware.net.proto.core.GTimeUnit;

public interface CacheStream {
	
	public void start(CacheStreamInput input) throws CacheException; 
	
	public void dispsoe();
	
	Map<String,CacheEntity> getEntities();
	
	public List<CacheEntitySignal> getSignals();
	
	public boolean hasEntity(String identifier); 
	
	public CacheEntity getEntity(String identifier) throws CacheException;
	
	public LocalDateTime getTime();
	
	public List<CacheEntitySignal> entitySignals(CacheEntity entity, GTimeUnit timeUnit, int timeValue, String signalType);
	
	public CacheSearchResults<CacheEntity> entitySearch(List<Predicate<CacheEntity>> predicates);
	
	public CacheSearchResults<CacheEntitySignal> signalSearch(List<Predicate<CacheEntitySignal>> predicates);
	
	public void insertSnapshot(int entityId, String entityIdentifier, LocalDateTime time, CacheValueSet vars);
	
	public void insertSignal(int signalId, String signalIdentifier, int entityId, String entityIdentifier, LocalDateTime time, CacheValueSet vars);
	
	public int getSnapshotCount();
	
	public void dispose(); 
	
	public CacheStreamInput getInput();
	
	public boolean storeSnapshots();
	
	
	
	
	

}
