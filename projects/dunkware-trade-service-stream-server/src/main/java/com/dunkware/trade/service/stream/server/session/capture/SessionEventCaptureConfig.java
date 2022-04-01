package com.dunkware.trade.service.stream.server.session.capture;

public class SessionEventCaptureConfig {
	
	private int workers = 1; 
	private String eventBrokers; 
	private String eventTopics; 
	
	private String streamId; 
	private String streamname; 
	
	private int streamSessionId;
	
	private String mongoCollection; 
	private String mongoURL; 

	public int getWorkers() {
		return workers;
	}

	public void setWorkers(int workers) {
		this.workers = workers;
	}

	public String getEventBrokers() {
		return eventBrokers;
	}

	public void setEventBrokers(String eventBrokers) {
		this.eventBrokers = eventBrokers;
	}

	public String getEventTopics() {
		return eventTopics;
	}

	public void setEventTopics(String eventTopics) {
		this.eventTopics = eventTopics;
	}

	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	public String getStreamname() {
		return streamname;
	}

	public void setStreamname(String streamname) {
		this.streamname = streamname;
	}

	public int getStreamSessionId() {
		return streamSessionId;
	}

	public void setStreamSessionId(int streamSessionId) {
		this.streamSessionId = streamSessionId;
	}

	public String getMongoCollection() {
		return mongoCollection;
	}

	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
	}

	public String getMongoURL() {
		return mongoURL;
	}

	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	} 
	
	
	
	
	

}
