package com.dunkware.xstream.data.cache.core;

import java.time.LocalDateTime;

import com.dunkware.xstream.data.cache.CacheEntitySnapshot;
import com.dunkware.xstream.data.cache.CacheValueSet;

public class CacheEntitySnapshotImpl implements CacheEntitySnapshot {
	
	private LocalDateTime time; 
	private CacheValueSet vars; 
	
	public CacheEntitySnapshotImpl(LocalDateTime time, CacheValueSet set) { 
		this.vars = set;
		this.time = time;
	}

	@Override
	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public CacheValueSet getVars() {
		return vars;
	}
	
	

}
