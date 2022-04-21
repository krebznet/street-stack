package com.dunkware.trade.service.data.util.cache.session;

import java.util.List;
import java.util.Map;

import com.dunkware.common.util.dtime.DTime;

public interface SessionEntity {
	
	String getIdentifier();
	
	Map<String,SessionSignal> getSignals();
	
	int getId();
	
	DTime lastSnapshot();
	
    List<Object> getSnapshots();
	
	SessionEntityVar getVar();
	

}
