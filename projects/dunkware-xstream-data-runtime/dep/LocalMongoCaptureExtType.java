package com.dunkware.xstream.data.capture;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;


public class LocalMongoCaptureExtType extends XStreamExtensionType{

	private String url; 
	private String database;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	} 
	private snapshotBulkInsert = 1000;
	
	
}
