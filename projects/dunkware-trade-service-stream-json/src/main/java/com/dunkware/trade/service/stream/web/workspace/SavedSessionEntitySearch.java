package com.dunkware.trade.service.stream.web.workspace;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public class SavedSessionEntitySearch {
	
	private long id; 
	private SessionEntitySearch search; 
	private double streamVersion;
	private String streamIdentifier; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	public double getStreamVersion() {
		return streamVersion;
	}
	public void setStreamVersion(double streamVersion) {
		this.streamVersion = streamVersion;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	
	
	

}
