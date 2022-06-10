package com.dunkware.xstream.net.protocol.message;

import com.dunkware.xstream.net.model.search.SessionEntitySearch;

public class SessionEntitySearchRequest {
	
	private int id; 
	private int streamId; 
	private SessionEntitySearch search;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	} 
	
	

}
