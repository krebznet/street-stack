package com.dunkware.trade.service.data.util.cache.session;

import com.dunkware.common.util.dtime.DTime;

public interface SessionEntityVar {

	String getIdentifier();
	
	int getId();
	
	Object getHigh();
	
	DTime getHighTime();
	
	Object getLow();
	
	DTime getLowTime();
	
	Object getValue();
	
	SessionEntity getEntity();
	
}
