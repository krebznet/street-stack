package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;
import com.dunkware.xstream.model.search.SessionEntitySearch;

public interface Container {
	
	public void start(ContainerInput input) throws ContainerException; 
	
	public void dispsoe();
	
	public DTimeZone getTimeZone();
	
	public ZoneOffset getZoneOffset();
	
	public ZoneId getZoneId();
	
	List<ContainerEntity> getEntities();
	
	public List<ContainerEntitySignal> getSignals();
	
	public boolean hasEntity(String identifier); 
	
	public ContainerEntity getEntity(String identifier) throws ContainerException;
	
	public LocalDateTime getDateTime(); // need date 
	
	public LocalDateTime getStartTime();
	
	public void addListener(ContainerListener listener);
	
	public void removeListener(ContainerListener listener);
	
	public void newSession();
	
	public ContainerEntityQuery entityQuery(SessionEntitySearch search) throws ContainerSearchException;
	
	public ContainerEntityScanner entityScanner(SessionEntityScanner scanner) throws ContainerSearchException;
	
	public void consumeStreamSnapshot(GEntitySnapshot snapshot);
	
	public void consumeStreamSignal(GEntitySignal signal);
	
	public void consumeStreamTime(GStreamTimeUpdate update);
	
	public int getSnapshotCount();
	
	public void dispose(); 
	
	public ContainerInput getInput();
	
	public DExecutor getExecutor();
	
	public boolean storeSnapshots();
	
	public boolean hasExtension(Class extClass); 
	
	public ContainerExtension getExtension(Class extClass) throws ContainerException;
	
	
	
	
	

}
