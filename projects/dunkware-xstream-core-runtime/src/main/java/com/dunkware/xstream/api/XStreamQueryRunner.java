package com.dunkware.xstream.api;

import java.util.List;

public interface XStreamQueryRunner {
	
	public void execute() throws XStreamQueryException;
	
	public boolean didExecute(); 
	
	public String getError(); 
	
	public List<XStreamRow> getResults();
	
	public boolean canExecute();
}

