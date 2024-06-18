package com.dunkware.xstream.api;

import java.time.LocalDateTime;
import java.util.Map;

import com.dunkware.xstream.xScript.SignalType;

public interface XSignalType {
	
	public SignalType getModel();
	
	public int getId();
	
	public void addListener(XSignalListener listener); 
	
	public void removeListener(XSignalListener listener);
	
	public String getName();

	XSignal signal(XStreamEntity entity, LocalDateTime dateTime, Map<Integer,Number> varSnapshots);
}
