package com.dunkware.trade.service.data.util.cache.session;

public interface SessionCache {

	void snapshot(Object snapshot);
	
	void signal(Object signal);
}
