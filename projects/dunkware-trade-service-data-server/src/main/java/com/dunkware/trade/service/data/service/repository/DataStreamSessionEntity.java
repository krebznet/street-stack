package com.dunkware.trade.service.data.service.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	@Column(name = "stream_name")
	private String streamName;
	
	@Column(name = "script_version")
	private double scriptVersion;
	
	@Column(name = "controller_start_time")
	private LocalDateTime controllerStartTime;
	
	@Column(name = "controller_stop_time")
	private LocalDateTime controllerStopTime;
	
	@Column(name = "session_start_time")
	private LocalDateTime sessionStartTime;
	
	@Column(name = "session_stop_time")
	private LocalDateTime sessionStopTime;
	
	@Column(name = "session_ident")
	private String sessionIdentifier; 
	 
	@Column(name = "session_state")
	private DataStreamSessionState state;
	
	@Column(name = "signal_count")
	private long signalCount;
	
	@Column(name = "snapshot_count")
	private long snapshotCount;
	
	@Column(name = "instrument_count")
	private long instrumentCount;;
	
	
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
	
	
	
	
	

	
	

}
