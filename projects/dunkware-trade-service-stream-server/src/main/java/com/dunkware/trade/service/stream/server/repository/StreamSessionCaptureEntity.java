package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dunkware.trade.service.data.json.enums.DataStreamSessionState;

@Entity(name = "StreamSessionCaptureEntity")
@Table(name = "stream_session_capture")
public class StreamSessionCaptureEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	
	
	@ManyToOne()
	private StreamEntity stream; 
	
	@Column(name = "Stream")
	private String streamName;
	
	@Column(name = "ScriptVersion")
	private double scriptVersion;
	
	@Column(name = "ControllerStart")
	private LocalDateTime controllerStartTime;
	
	@Column(name = "ControllerStop")
	private LocalDateTime controllerStopTime;
	
	@Column(name = "SessionStart")
	private LocalDateTime sessionStartTime;
	
	@Column(name = "SessionStop")
	private LocalDateTime sessionStopTime;
	
	@Column(name = "Identifier")
	private String sessionIdentifier; 
	
	@Enumerated(EnumType.STRING)
	@Column(name = "State")
	private DataStreamSessionState state;
	
	@Column(name = "Signals")
	private long signalCount = 0;
	
	@Column(name = "Snapshots")
	private long snapshotCount = 0;
	
	@Column(name = "Instruments")
	private long instrumentCount = 0;
	
	@Column(name = "SnapshotCompletion")
	private LocalDateTime snapshotCompleteTime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public StreamEntity getStream() {
		return stream;
	}
	public void setStream(StreamEntity stream) {
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
	public LocalDateTime getControllerStartTime() {
		return controllerStartTime;
	}
	public void setControllerStartTime(LocalDateTime controllerStartTime) {
		this.controllerStartTime = controllerStartTime;
	}
	public LocalDateTime getControllerStopTime() {
		return controllerStopTime;
	}
	public void setControllerStopTime(LocalDateTime controllerStopTime) {
		this.controllerStopTime = controllerStopTime;
	}
	public LocalDateTime getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(LocalDateTime sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public LocalDateTime getSessionStopTime() {
		return sessionStopTime;
	}
	public void setSessionStopTime(LocalDateTime sessionStopTime) {
		this.sessionStopTime = sessionStopTime;
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
	public long getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(long signalCount) {
		this.signalCount = signalCount;
	}
	public long getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public long getInstrumentCount() {
		return instrumentCount;
	}
	public void setInstrumentCount(long instrumentCount) {
		this.instrumentCount = instrumentCount;
	}
	public LocalDateTime getSnapshotCompleteTime() {
		return snapshotCompleteTime;
	}
	public void setSnapshotCompleteTime(LocalDateTime snapshotCompleteTime) {
		this.snapshotCompleteTime = snapshotCompleteTime;
	}
	
	
	
	
	
	
	

	
	

}
