package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.dunkware.net.proto.netstream.GEntityCriteriaVar;
import com.dunkware.net.proto.stream.GEntitySnapshot;

public interface ContainerEntity {
	
	 Map<LocalDateTime,ContainerEntitySnapshot> getSnapshots();
	 
	 Vector<ContainerEntitySignal> getSignals();
	 
	 String getIdent();
	 
	 int getId();
	 
	 ContainerEntitySnapshot getLastSnapshot();
	 
	 public int getSnapshotCount();
	 
	 public ContainerEntitySnapshot getBackSnapshot(int backCount) throws ContainerSearchException;
	 
	 void consumeSignal(ContainerEntitySignal signal);
	 	 
	 public void consumeSnapshot(ContainerEntitySnapshot snapshot);
	 
	 public void addListener(ContainerEntityListener listener);
	 
	 public void removeListener(ContainerEntityListener listener);
	 
	 List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop);
	 
	 List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes);
	 
	 int getSignalCount(LocalDateTime from, LocalDateTime to, String... types);
	 
	 public Container getContainer();
	 
	 public Object resolveCriteriaVar(GEntityCriteriaVar var) throws ContainerException,ContainerSearchException;

}
