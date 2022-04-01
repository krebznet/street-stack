package com.dunkware.xstream.data.cache;

public interface CacheEntitySignalMatcher extends CacheMatcher {
	
	default void update(CacheStream stream) { 
		
	}

	boolean match(CacheEntitySignal signal) throws CacheSearchException;
}
