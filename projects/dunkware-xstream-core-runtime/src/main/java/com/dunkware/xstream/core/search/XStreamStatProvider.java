package com.dunkware.xstream.core.search;

import com.dunkware.xstream.api.XStreamVar;

public interface XStreamStatProvider {
	
	public Number getVarHistoricalHigh(XStreamVar var, int relativeSessions);
	
	

}
