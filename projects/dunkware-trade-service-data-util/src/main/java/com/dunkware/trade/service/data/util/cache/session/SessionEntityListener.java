package com.dunkware.trade.service.data.util.cache.session;

public interface SessionEntityListener {

	public void entitySnapshot(SessionEntity entity, Object snapshot);
	
	public void entitySignal(SessionEntity entity, Object signal);
}
