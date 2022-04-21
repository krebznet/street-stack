package com.dunkware.logger.core;

import java.util.Map;

import com.dunkware.logger.DLog;
import com.dunkware.logger.DLogger;

public class DLogImpl implements DLog {

	private DLogger logger; 
	private String messsage; 
	private String[] tags;
	private Map<String,String> labels; 
	private int lineNumber;
	
	
	public DLogger getLogger() {
		return logger;
	}
	public void setLogger(DLogger logger) {
		this.logger = logger;
	}
	public String getMesssage() {
		return messsage;
	}
	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
