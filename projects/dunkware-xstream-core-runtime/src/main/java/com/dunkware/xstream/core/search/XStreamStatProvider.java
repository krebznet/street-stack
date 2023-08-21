package com.dunkware.xstream.core.search;

import com.dunkware.xstream.api.XStreamEntityVar;

public interface XStreamStatProvider {
	
	public Number getVarHistoricalHigh(XStreamEntityVar var, int relativeSessions);
	
	

}
