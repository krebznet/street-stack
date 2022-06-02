package com.dunkware.trade.service.stream.server.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "stream_session_entity")
public class StreamSessionEntEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "ent_id")
	private int entId; 
	@Column(name = "ent_ident")
	private String entIdentifier; 
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
	

	
	@ManyToOne
	private StreamSessionEntity session;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StreamSessionEntity getSession() {
		return session;
	}
	public void setSession(StreamSessionEntity session) {
		this.session = session;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getEntIdentifier() {
		return entIdentifier;
	}
	public void setEntIdentifier(String entIdentifier) {
		this.entIdentifier = entIdentifier;
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
