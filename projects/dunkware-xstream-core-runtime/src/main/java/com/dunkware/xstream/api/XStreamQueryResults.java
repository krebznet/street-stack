package com.dunkware.xstream.api;

import java.util.Collection;

public interface XQueryResults {
	
	public double getMilliseconds();
	
	public Collection<XStreamRow> getRows();
	
	public boolean didResolve();
	
	public boolean didEvaulate();
	
	public boolean didFail(); 

	public Collection<String> getMessages();
	
	

}
