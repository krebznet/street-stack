package com.dunkware.utils.kafka.admin.model;

import java.util.ArrayList;
import java.util.List;

public class KafkaDeleteTopicResults {
	
	private double time; 
	private List<KafkaDeleteTopicResult> deletes = new ArrayList<KafkaDeleteTopicResult>();
	private List<KafkaDeleteTopicResult> exceptions = new ArrayList<KafkaDeleteTopicResult>();
	private List<String> timeoutTopics = new ArrayList<String>();
	
	private boolean timeout = false; 
	private int exceptionCount = 0;
	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public List<KafkaDeleteTopicResult> getDeletes() {
		return deletes;
	}
	public void setDeletes(List<KafkaDeleteTopicResult> deletes) {
		this.deletes = deletes;
	}
	public List<KafkaDeleteTopicResult> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<KafkaDeleteTopicResult> exceptions) {
		this.exceptions = exceptions;
	}
	public int getExceptionCount() {
		return exceptionCount;
	}
	public void setExceptionCount(int exceptionCount) {
		this.exceptionCount = exceptionCount;
	}
	
	public void addResult(KafkaDeleteTopicResult result) { 
		if(result.isException()) { 
			exceptionCount++;
			exceptions.add(result);
		} else { 
			deletes.add(result);
		}
	}
	
	public boolean hasExceptions() { 
		if(exceptionCount > 0) { 
			return true; 
		}
		return false; 
	}
	public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}
	public List<String> getTimeoutTopics() {
		return timeoutTopics;
	}
	public void setTimeoutTopics(List<String> timeoutTopics) {
		this.timeoutTopics = timeoutTopics;
	}
	
	
	
	
	

}
