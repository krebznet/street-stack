package com.dunkware.xstream.api;

import java.util.Collection;

public interface XStreamQueryResults {
	
	public double getMilliseconds();
	
	public Collection<XStreamRow> getRows();
	
	public boolean didResolve();
	
	public boolean didEvaulate();
	
	public boolean didFail(); 

	public Collection<String> getMessages();
	
	

}
