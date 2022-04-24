package com.dunkware.trade.service.data.util.cache.session.core;

import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.data.util.cache.session.SessionCache;
import com.dunkware.trade.service.data.util.cache.session.SessionEntity;

public class SessionCacheImpl implements SessionCache {

	private Map<String,SessionEntity> entities = new ConcurrentHashMap<String,SessionEntity>();
	
	@Override
	public void snapshot(Object snapshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signal(Object signal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTimeZone getTimeZone() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	
}
