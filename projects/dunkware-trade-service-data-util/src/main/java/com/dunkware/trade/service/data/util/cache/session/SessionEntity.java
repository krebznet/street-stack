package com.dunkware.trade.service.data.util.cache.session;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.json.stream.cache.SessionEntityStats;

public interface SessionEntity {
	
	String getIdentifier();
	
    Map<LocalDateTime,GEntitySnapshot> getSnapshots();
	
	Map<String,SessionEntitySignalType> getSignalTypes();
	
	int getId();
	
	GEntitySnapshot lastSnapshot();

	SessionEntityVar getVar(String identifier);
	
	Collection<SessionEntityVar> getVars();
	
	void snapshot(GEntitySnapshot snapshot);
	
	void signal(GEntitySignal signal);
	
	void addListener(SessionEntityListener listener);
	
	void removeListener(SessionEntityListener listener);
	
	public SessionCache getCache();
	
	public SessionEntityStats getStats();
	

}
