package com.dunkware.xstream.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.xstream.model.signal.type.XStreamSignalType;

public interface XStreamSignals {

	public int getSignalCount();
	
	public Collection<XStreamSignalType> getSiganlTypes();
	
	public void addListener(XStreamSignalListener listener);
	
	public void removeListener(XStreamSignalListener listener);
	
	public void startSignalType(XStreamSignalType type) throws Exception; 
	
	public void stopSignalType(int id) throws Exception; 
	
	public List<XStreamSignal> search(XStreamSignalSearch search);
	

}
