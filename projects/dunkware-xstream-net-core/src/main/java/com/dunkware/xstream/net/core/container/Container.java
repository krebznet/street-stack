package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.data.GTimeUnit;
import com.dunkware.net.proto.stream.GEntityMatcher;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;

public interface Container {
	
	public void start(ContainerInput input) throws ContainerException; 
	
	public void dispsoe();
	
	public DTimeZone getTimeZone();
	
	List<ContainerEntity> getEntities();
	
	public List<ContainerEntitySignal> getSignals();
	
	public boolean hasEntity(String identifier); 
	
	public ContainerEntity getEntity(String identifier) throws ContainerException;
	
	public LocalDateTime getTime();
	
	public void addTimeListener(ContainerTimeListener listener);
	
	public void removeTimeListener(ContainerTimeListener listener);
	
	public List<ContainerEntitySignal> entitySignals(ContainerEntity entity, GTimeUnit timeUnit, int timeValue, String signalType);
	
	public void deleteContainer();
	
	public ContainerSearchResults<ContainerEntity> entitySearch(GEntityMatcher matcher) throws ContainerException;
	
	public ContainerSearchResults<ContainerEntity> entitySearch(List<Predicate<ContainerEntity>> predicates);
	
	public void consumeStreamSnapshot(GEntitySnapshot snapshot);
	
	public void consumeStreamSignal(GEntitySignal signal);
	
	public void consumeStreamTime(GStreamTimeUpdate update);
	
	public int getSnapshotCount();
	
	public void dispose(); 
	
	public ContainerInput getInput();
	
	public DExecutor getExecutor();
	
	public boolean storeSnapshots();
	
	
	
	
	

}
