package com.dunkware.xstream.data.cache;

public interface CacheEntityMatcher {
	
	boolean match(CacheEntity entity) throws CacheSearchException;

}
