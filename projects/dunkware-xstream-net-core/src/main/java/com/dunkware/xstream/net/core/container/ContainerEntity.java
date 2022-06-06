package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dunkware.net.proto.stream.GEntityCriteriaVar;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;

public interface ContainerEntity {

	String getIdent();

	int getId();

	GEntitySnapshot getLastSnapshot();

	public Collection<ContainerEntityVar> getVars();

	public ContainerEntityVar getVar(String ident);

	public int getSnapshotCount();

	public Object getLastVarValue(String ident);
	
	public Map<String,Object> getLastVarValues();
	
	void consumeSignal(GEntitySignal signal);
	
	public boolean varExists(String ident);

	public void consumeSnapshot(GEntitySnapshot snapshot);

	public void addListener(ContainerEntityListener listener);

	public void removeListener(ContainerEntityListener listener);

	List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop);

	List<ContainerEntitySignal> getSignals(LocalDateTime start, LocalDateTime stop, String... signalTypes);

	int getSignalCount(LocalDateTime from, LocalDateTime to, String... types);

	public Container getContainer();

	public Object resolveCriteriaVar(GEntityCriteriaVar var) throws ContainerException, ContainerSearchException;

}
