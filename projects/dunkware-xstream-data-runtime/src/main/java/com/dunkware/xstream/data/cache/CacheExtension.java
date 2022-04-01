package com.dunkware.xstream.data.cache;

public interface CacheExtension {
	
	void init(CacheStream stash, CacheExtensionType type) throws CacheException;
	
	void stashStarting(CacheStream stash) throws CacheException;
	
	void stashStarted(CacheStream stash) throws CacheException;
	
	void stashDisposing(CacheStream stash);
	
	void stashDisposed(CacheStream stash);

}
