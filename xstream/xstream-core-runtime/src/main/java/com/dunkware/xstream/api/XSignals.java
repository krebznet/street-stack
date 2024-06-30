package com.dunkware.xstream.api;

import java.util.Collection;

public interface XSignals {

	public void addSignalListener(XSignalListener listener); 
	
	public void removeSignalListener(XSignalListener listener);
	
	public Collection<XSignalType> getSignalTypes();


}
