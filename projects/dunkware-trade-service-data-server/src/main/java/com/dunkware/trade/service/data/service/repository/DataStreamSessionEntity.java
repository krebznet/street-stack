package com.dunkware.trade.service.data.service.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;

@Entity(name = "DataStreamSessionEntity")
@Table(name = "stream_session")
public class DataStreamSessionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	
	
	@ManyToOne()
	private DataStreamEntity stream; 
	
	private String streamName; 
	private double scriptVersion;
	
	private LocalDateTime messageStartDateTime; 
	private LocalDateTime messageStopDateTime;
	
	private LocalDateTime sessionStartDateTime;
	private LocalDateTime sessionEndDateTime;
	
	private String sessionIdentifier; 
	 
	
	private DataStreamSessionState state;
	
	private long signalWriteCount;
	private long snapshotBucketWriteCount;
	private long snapshotWriteCount;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public DataStreamEntity getStream() {
		return stream;
	}
	public void setStream(DataStreamEntity stream) {
		this.stream = stream;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public double getScriptVersion() {
		return scriptVersion;
	}
	public void setScriptVersion(double scriptVersion) {
		this.scriptVersion = scriptVersion;
	}
	public LocalDateTime getMessageStartDateTime() {
		return messageStartDateTime;
	}
	public void setMessageStartDateTime(LocalDateTime messageStartDateTime) {
		this.messageStartDateTime = messageStartDateTime;
	}
	public LocalDateTime getMessageStopDateTime() {
		return messageStopDateTime;
	}
	public void setMessageStopDateTime(LocalDateTime messageStopDateTime) {
		this.messageStopDateTime = messageStopDateTime;
	}
	public LocalDateTime getSessionStartDateTime() {
		return sessionStartDateTime;
	}
	public void setSessionStartDateTime(LocalDateTime sessionStartDateTime) {
		this.sessionStartDateTime = sessionStartDateTime;
	}
	public LocalDateTime getSessionEndDateTime() {
		return sessionEndDateTime;
	}
	public void setSessionEndDateTime(LocalDateTime sessionEndDateTime) {
		this.sessionEndDateTime = sessionEndDateTime;
	}
	public String getSessionIdentifier() {
		return sessionIdentifier;
	}
	public void setSessionIdentifier(String sessionIdentifier) {
		this.sessionIdentifier = sessionIdentifier;
	}
	
	public DataStreamSessionState getState() {
		return state;
	}
	public void setState(DataStreamSessionState state) {
		this.state = state;
	}
	public long getSignalWriteCount() {
		return signalWriteCount;
	}
	public void setSignalWriteCount(long signalWriteCount) {
		this.signalWriteCount = signalWriteCount;
	}
	public long getSnapshotBucketWriteCount() {
		return snapshotBucketWriteCount;
	}
	public void setSnapshotBucketWriteCount(long snapshotBucketWriteCount) {
		this.snapshotBucketWriteCount = snapshotBucketWriteCount;
	}
	public long getSnapshotWriteCount() {
		return snapshotWriteCount;
	}
	public void setSnapshotWriteCount(long snapshotWriteCount) {
		this.snapshotWriteCount = snapshotWriteCount;
	}
	
	
	
	
	

	
	

}
