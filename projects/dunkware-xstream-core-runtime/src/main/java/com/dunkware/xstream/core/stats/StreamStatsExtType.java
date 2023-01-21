package com.dunkware.xstream.core.stats;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class StreamStatsExtType extends XStreamExtensionType {
	
	private String streamIdent; 
	private long sessionId; 
	private String server;
	
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	} 
	
	

}
