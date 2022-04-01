package com.dunkware.trade.service.data.service.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.dunkware.trade.service.data.json.controller.spec.StreamEntitySpec;
import com.dunkware.trade.service.data.json.enums.StreamSessionState;

/**
 * This will be inserted into MongoDB collection on every session capture completion
 * 
 * @author duncankrebs
 *
 */
@Document("data_service_sessions")
public class StreamSessionDO {

	@Id
	private ObjectId _id;
	private String stream;
	private String sessionId; 
	private LocalDate date;
	private LocalTime streamStartTime; 
	private LocalTime streamStopTime; 
	private LocalTime sessionStartTime;
	private LocalTime sessionStopTime; 
	private int entityCount; 
	private double scriptVersion; 
	private String timeZone;
	private long snapshotCount; 
	private long signalWriteCount; 
	private long snapshotWriteCount; 
	private StreamSessionState state; 
		
	@Field
	private List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStreamStartTime() {
		return streamStartTime;
	}

	public void setStreamStartTime(LocalTime streamStartTime) {
		this.streamStartTime = streamStartTime;
	}

	public LocalTime getStreamStopTime() {
		return streamStopTime;
	}

	public void setStreamStopTime(LocalTime streamStopTime) {
		this.streamStopTime = streamStopTime;
	}

	public LocalTime getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(LocalTime sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public LocalTime getSessionStopTime() {
		return sessionStopTime;
	}

	public void setSessionStopTime(LocalTime sessionStopTime) {
		this.sessionStopTime = sessionStopTime;
	}

	public int getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}

	public double getScriptVersion() {
		return scriptVersion;
	}

	public void setScriptVersion(double scriptVersion) {
		this.scriptVersion = scriptVersion;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public long getSnapshotCount() {
		return snapshotCount;
	}

	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}

	public long getSignalWriteCount() {
		return signalWriteCount;
	}

	public void setSignalWriteCount(long signalWriteCount) {
		this.signalWriteCount = signalWriteCount;
	}

	public long getSnapshotWriteCount() {
		return snapshotWriteCount;
	}

	public void setSnapshotWriteCount(long snapshotWriteCount) {
		this.snapshotWriteCount = snapshotWriteCount;
	}

	public List<StreamEntitySpec> getEntities() {
		return entities;
	}

	public void setEntities(List<StreamEntitySpec> entities) {
		this.entities = entities;
	}

	public StreamSessionState getState() {
		return state;
	}

	public void setState(StreamSessionState state) {
		this.state = state;
	}
	
	
	
	

	
	
	

}