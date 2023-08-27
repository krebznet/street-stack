package com.dunkware.xstream.api;

import java.util.List;
import java.util.Map;

public interface XStreamEntityQueryRun {
	
	public static final int ERRORS = 0;
	public static final int SUCCESS = 1;
	
	public double getTime();
	
	public int getQueryCount();
	
	public int getExceptionCount();
	
	public int getReturnCode();
	
	public int getUnresolvedCount();
	
	public int getResolvedCount();
	
	public List<String> getExceptions();
	
	public List<XStreamEntity> getEntities();
	
	
	
}
