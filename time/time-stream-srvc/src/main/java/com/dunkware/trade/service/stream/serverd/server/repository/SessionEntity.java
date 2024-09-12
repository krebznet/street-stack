package com.dunkware.trade.service.stream.serverd.server.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "stream_session_entity")
public class SessionEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "ent_id")
	private int entId; 
	@Column(name = "ent_ident")
	private String entIdentifier; 
	@Column(name = "snapshot_count")
	private long snapshotCount; 
	@Column(name = "signal_count")
	private long signalCount;   
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
	
	

	
	
	
	

}
