package com.dunkware.trade.service.stream.json.controller.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StreamSessionSpec {
	
	private String kafkaBrokers;
	
	private LocalDate date;
	private LocalTime startTime; 
	private LocalTime stopTime;
	private String timeZone; 
	private int entityCount;
//	private StreamScript streamScript;
	private String streamIdentifier;
	private String sessionId;
	
	private String kafkaSnapshotTopic; 
	private String kafkaSignalTopic; 
	
    private List<StreamEntitySpec> streamEntities = new ArrayList<StreamEntitySpec>();
    
    
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	//public StreamScript getStreamScript() {
	//	return streamScript;
	//}
	///public void setStreamScript(StreamScript streamScript) {
	//	this.streamScript = streamScript;
	//}
	public List<StreamEntitySpec> getStreamEntities() {
		return streamEntities;
	}
	public void setStreamEntities(List<StreamEntitySpec> streamEntities) {
		this.streamEntities = streamEntities;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	public String getKafkaSnapshotTopic() {
		return kafkaSnapshotTopic;
	}
	public void setKafkaSnapshotTopic(String kafkaSnapshotTopic) {
		this.kafkaSnapshotTopic = kafkaSnapshotTopic;
	}
	public String getKafkaSignalTopic() {
		return kafkaSignalTopic;
	}
	public void setKafkaSignalTopic(String kafkaSignalTopic) {
		this.kafkaSignalTopic = kafkaSignalTopic;
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Stream:");
		b.append(getStreamIdentifier());
		b.append(",");
		b.append("SessionID:");
		b.append(sessionId);
		b.append("StartTime:").append(startTime.toString()).append(",").append("StopTime:").append(stopTime.toString())
		.append(",").append("Date:").append(date.toString()).append(",TimeZone:").append(timeZone)
		.append(",Entity Count:").append(streamEntities.size()).append(",KafkaBrokers:").append(kafkaBrokers);
		
		return b.toString();
	}

	
	
	
	
	
	
    
    
}
