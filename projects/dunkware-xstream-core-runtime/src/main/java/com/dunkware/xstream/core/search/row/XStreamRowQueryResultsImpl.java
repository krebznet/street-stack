package com.dunkware.xstream.core.search.row;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamRowQueryResults;

public class XStreamRowQueryResultsImpl implements XStreamRowQueryResults {

	private double time = 0.0;
	private int queryCount = 0;
	private int exceptionCount = 0;
	private int unresolvedCount = 0;
	private int resolvedCount = 0;
	private Map<String,String> exceptionMap = new ConcurrentHashMap<String,String>();
	
	
	public void addException(String rowIDent, String exception) { 
		this.exceptionCount++;
		this.exceptionMap.put(rowIDent, exception);
	}
	
	public void incrementResolvedCount() { 
		resolvedCount++;
	}
	
	@Override
	public int getResolvedCount() {
		// TODO Auto-generated method stub
		return 0;
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
	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}

	@Override
	public List<XStreamEntity> getMatched() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
