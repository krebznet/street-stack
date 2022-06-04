package com.dunkware.trade.service.data.util.cache.session;

import com.dunkware.common.util.dtime.DTimeZone;

public interface SessionCache {

	void snapshot(Object snapshot);
	
	void signal(Object signal);
	
	void start();
	
	void stop();
	
	DTimeZone getTimeZone();
}
