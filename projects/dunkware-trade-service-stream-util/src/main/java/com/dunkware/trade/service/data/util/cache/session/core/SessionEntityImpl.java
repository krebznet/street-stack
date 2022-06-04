package com.dunkware.trade.service.data.util.cache.session.core;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GEntityVarSnapshot;
import com.dunkware.trade.service.data.json.stream.cache.SessionEntityStats;
import com.dunkware.trade.service.data.util.cache.session.SessionCache;
import com.dunkware.trade.service.data.util.cache.session.SessionEntity;
import com.dunkware.trade.service.data.util.cache.session.SessionEntityListener;
import com.dunkware.trade.service.data.util.cache.session.SessionEntitySignalType;
import com.dunkware.trade.service.data.util.cache.session.SessionEntityVar;

public class SessionEntityImpl implements SessionEntity {

	private String identifier; 
	private int id;
	private Map<LocalDateTime,GEntitySnapshot> snapshots = new ConcurrentHashMap<LocalDateTime,GEntitySnapshot>();
	private Map<String,SessionEntitySignalType> signals = new ConcurrentHashMap<String, SessionEntitySignalType>();
	private Map<String,SessionEntityVar> variables = new ConcurrentHashMap<String, SessionEntityVar>();
	private SessionCacheImpl cache;
	
	public void start(GEntitySnapshot snapshot, SessionCacheImpl cache) { 
		this.identifier = snapshot.getIdentifier();
		this.id = snapshot.getId();
		this.cache = cache;
		for (GEntityVarSnapshot varSnapshot : snapshot.getVarsList()) {
			varSnapshot.getDataType();
			// figure this out; 
		}
		//snapshot(snapshot);
	}
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	

	@Override
	public Map<String, SessionEntitySignalType> getSignalTypes() {
		return signals;
	}

	@Override
	public int getId() {
		return id;
	}


	@Override
	public Map<LocalDateTime, GEntitySnapshot> getSnapshots() {
		return snapshots;
	}



	@Override
	public GEntitySnapshot lastSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public SessionCache getCache() {
		return cache;
	}

	@Override
	public SessionEntityVar getVar(String identifier) {
		return variables.get(identifier);
	}

	@Override
	public Collection<SessionEntityVar> getVars() {
		return variables.values();
	}

	@Override
	public void snapshot(GEntitySnapshot snapshot) {
		snapshots.put(DProtoHelper.toLocalDateTime(snapshot.getTime(), cache.getTimeZone()),snapshot);
		
	}

	@Override
	public void signal(GEntitySignal signal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(SessionEntityListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(SessionEntityListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SessionEntityStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
