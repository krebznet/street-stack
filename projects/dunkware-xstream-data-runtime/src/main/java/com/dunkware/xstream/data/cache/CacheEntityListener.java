package com.dunkware.xstream.data.cache;

public interface CacheEntityListener {
	
	public void init(String entityId, String entityIdentifier);
	
	void entitySignal(CacheEntitySignal signal);
	
	void entitySnapshot(CacheValueSet snapshot);
	
}
