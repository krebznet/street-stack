package com.dunkware.trade.net.data.server.stream;

public class StreamDataConfig {
	
	private String mongoURL; 
	private String streamIdentifier; 
	private int streamId;
	
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	} 
	
	
	

}
