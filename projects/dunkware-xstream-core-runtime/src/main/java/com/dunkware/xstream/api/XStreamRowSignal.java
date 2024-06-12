package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import com.dunkware.xstream.xScript.SignalType;

public interface XStreamRowSignal {

	public XStreamEntity getRow();

	public SignalType getSignalType();
	
	long getTimestamp(); 
	
	LocalTime getTime();
	
	LocalDateTime getLocalDateTime();
	
	Map<Integer,Object> getVars();
}
