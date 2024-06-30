package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.util.Map;

public interface XSignal {
	
	XStreamEntity getEntity(); 
	
	LocalDateTime getTimeStamp();
	
	public XSignalType getType();
	
	Map<Integer,Number> getNumericVariables();
	

}
