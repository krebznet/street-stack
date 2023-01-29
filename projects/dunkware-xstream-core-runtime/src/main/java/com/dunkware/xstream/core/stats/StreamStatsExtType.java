package com.dunkware.xstream.core.stats;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class StreamStatsExtType extends XStreamExtensionType {
	
	private String streamIdent; 
	private String sessionId; 
	private String postURL;
	
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	
	public String getPostURL() {
		return postURL;
	}
	public void setPostURL(String postURL) {
		this.postURL = postURL;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	

	

}
