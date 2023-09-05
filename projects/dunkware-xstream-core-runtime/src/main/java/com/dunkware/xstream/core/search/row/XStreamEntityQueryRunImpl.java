package com.dunkware.xstream.core.search.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityQueryRun;

public class XStreamEntityQueryRunImpl implements XStreamEntityQueryRun {

	private double time = 0.0;
	private int queryCount = 0;
	private int exceptionCount = 0;
	private int unresolvedCount = 0;
	private int resolvedCount = 0;
	private int returnCode = XStreamEntityQueryRun.SUCCESS;
	private List<XStreamEntity> entities = null;
	private List<String> exceptions = new ArrayList<String>();
	private String lastException = null;
	
	public void addException(String exception) { 
		this.exceptionCount++;
		this.exceptions.add(exception);
		this.lastException = exception;
		returnCode = XStreamEntityQueryRun.ERRORS;
	}
	
	public void incrementResolvedCount() { 
		resolvedCount++;
	}
	
	
	
	@Override
	public String getLastException() {
		return lastException;
	}

	@Override
	public int getResolvedCount() {
		return resolvedCount;
	}
	
	@Override
	public int getReturnCode() {
		return returnCode;
	}

	@Override
	public List<XStreamEntity> getEntities() {
		return entities;
	}

	public void incremenetUnresolvedCount() { 
		unresolvedCount++;
	}
	public void setTime(double time) {
		this.time = time;
	}

	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}

	@Override
	public double getTime() {
		return time;
	}

	@Override
	public int getQueryCount() {
		return queryCount; 
	}

	@Override
	public int getExceptionCount() {
		return exceptionCount;
	}

	@Override
	public int getUnresolvedCount() {
		return unresolvedCount;
	}

	@Override
	public List<String> getExceptions() {
		return exceptions;
	}
	
	


	
	
	
	
	

}
