package com.dunkware.trade.service.data.service.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;


@Entity(name = "DataStreamSessionInstrumentEntity")
@Table(name = "stream_session_instrument")
public class DataStreamSessionInstrumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "inst_id")
	private int instId; 
	@Column(name = "inst_ident")
	private String instIdentifier; 
	@Column(name = "script_version")
	private double scriptVersion; 
	@Column(name = "snapshot_count")
	private long snapshotCount; 
	@Column(name = "signal_count")
	private long signalCount;   
	@Column(name = "stream_name")
	private String streamName; 
	@Column(name = "session_ident")
	private String sessionIdentifier; 
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getInstId() {
		return instId;
	}

	public void setInstId(int instId) {
		this.instId = instId;
	}

	public String getInstIdentifier() {
		return instIdentifier;
	}

	public void setInstIdentifier(String instIdentifier) {
		this.instIdentifier = instIdentifier;
	}

	public double getScriptVersion() {
		return scriptVersion;
	}

	public void setScriptVersion(double scriptVersion) {
		this.scriptVersion = scriptVersion;
	}

	public long getSnapshotCount() {
		return snapshotCount;
	}

	public void setSnapshotCount(long snapshotCount) {
		this.snapshotCount = snapshotCount;
	}

	public long getSignalCount() {
		return signalCount;
	}

	public void setSignalCount(long signalCount) {
		this.signalCount = signalCount;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getSessionIdentifier() {
		return sessionIdentifier;
	}

	public void setSessionIdentifier(String sessionIdentifier) {
		this.sessionIdentifier = sessionIdentifier;
	}

}
