package com.dunkware.xstream.model.metrics;

public class XStreamVarMetrics {

	private String name; 
	private int size; 
	private long setCount;
	private long getCount;
	public XStreamVarMetrics()  {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getSetCount() {
		return setCount;
	}
	public void setSetCount(long setCount) {
		this.setCount = setCount;
	}
	public long getGetCount() {
		return getCount;
	}
	public void setGetCount(long getCount) {
		this.getCount = getCount;
	}
	
	
	
	
	
	
}
