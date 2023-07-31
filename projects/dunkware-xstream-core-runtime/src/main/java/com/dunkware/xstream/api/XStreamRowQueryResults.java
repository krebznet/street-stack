package com.dunkware.xstream.api;

import java.util.List;
import java.util.Map;

public interface XStreamRowQueryResults {
	
	public double getTime();
	
	public int getQueryCount();
	
	public int getExceptionCount();
	
	public int getUnresolvedCount();
	
	public int getResolvedCount();
	
	public Map<String,String> getExceptionMap();
	
	public List<XStreamRow> getMatched();
	
}
