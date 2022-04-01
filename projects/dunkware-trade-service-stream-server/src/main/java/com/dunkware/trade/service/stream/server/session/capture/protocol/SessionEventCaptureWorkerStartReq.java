package com.dunkware.trade.service.stream.server.session.capture.protocol;

public class SessionEventCaptureWorkerStartReq {
	
	private String streamId; 
	private String streamName; 
	
	private String mongoURL; 
	private String mongoCollection; 
	
	private String eventBrokers; 
	private String eventTopics; 
	
	private String kafkaClient; 
	private String kafkaGroup;
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getMongoURL() {
		return mongoURL;
	}
	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}
	public String getMongoCollection() {
		return mongoCollection;
	}
	public void setMongoCollection(String mongoCollection) {
		this.mongoCollection = mongoCollection;
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
	public String getKafkaClient() {
		return kafkaClient;
	}
	public void setKafkaClient(String kafkaClient) {
		this.kafkaClient = kafkaClient;
	}
	public String getKafkaGroup() {
		return kafkaGroup;
	}
	public void setKafkaGroup(String kafkaGroup) {
		this.kafkaGroup = kafkaGroup;
	} 
	
	

}
