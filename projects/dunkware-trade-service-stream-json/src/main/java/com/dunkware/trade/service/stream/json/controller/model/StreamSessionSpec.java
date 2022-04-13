package com.dunkware.trade.service.stream.json.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.model.script.StreamScript;

/**
 * Defines a running stream session and the attributes needed to connect 
 * to the kafka topics the publish stream entity snapshots and signals. 
 * Also lists the stream entities that are part of this session as well
 * as the stream script being used. the start/stop time the date and the 
 * TimeZone
 * @author duncankrebs
 *
 */
public class StreamSessionSpec {
	
	private String kafkaBrokers;
	
	private DDate date;
	private DTime startTime; 
	private DTime stopTime;
	private DTimeZone timeZone; 
	private int entityCount;
	private StreamScript streamScript;
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
	
	public DTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}
	public DTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(DTime stopTime) {
		this.stopTime = stopTime;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public StreamScript getStreamScript() {
		return streamScript;
	}
	public void setStreamScript(StreamScript streamScript) {
		this.streamScript = streamScript;
	}
	public List<StreamEntitySpec> getStreamEntities() {
		return streamEntities;
	}
	public void setStreamEntities(List<StreamEntitySpec> streamEntities) {
		this.streamEntities = streamEntities;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
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
		b.append("StartTime:").append(startTime.toHHmmSS()).append(",").append("StopTime:").append(stopTime.toHHmmSS())
		.append(",").append("Date:").append(date.toMMDDYY()).append(",TimeZone:").append(timeZone.name())
		.append(",Entity Count:").append(streamEntities.size()).append(",KafkaBrokers:").append(kafkaBrokers);
		
		return b.toString();
	}

	
	
	
	
	
	
    
    
}
