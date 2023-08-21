package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.util.Map;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.xstream.xScript.SignalType;

public interface XStreamRowSignal {

	public XStreamEntity getRow();

	public SignalType getSignalType();
	
	long getTimestamp(); 
	
	DTime getTime();
	
	LocalDateTime getLocalDateTime();
	
	Map<Integer,Object> getVars();
}
