package com.dunkware.xstream.api;

import java.util.Collection;

import com.dunkware.xstream.model.signal.type.XStreamSignalType;

public interface XStreamSignals {
	
	public Collection<XStreamSignalType> getSiganlTypes();
	
	public void addListener(XStreamSignalListener listener);
	
	public void removeListener(XStreamSignalListener listener);
	
	public void startSignalType(XStreamSignalType type) throws Exception; 
	
	public void stopSignalType(int id) throws Exception; 
	
	

}
