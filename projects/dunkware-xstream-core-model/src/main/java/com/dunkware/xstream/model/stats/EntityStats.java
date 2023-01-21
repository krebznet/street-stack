package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;
import java.util.List;

public class EntityStats {
	
	public static EntityStats newInstance(LocalDateTime fromTime) { 
		EntityStats stats = new EntityStats();
		stats.setFrom(fromTime);
		return stats;
	}

	private LocalDateTime from; 
	private LocalDateTime to; 
	private int streamId;
	private String entityIdent; 
	private int entityId; 
	private String sessionId; 
	private String streamIdent; 
	private double streamVersion; 
	private List<EntityVarStats> varStats;
	
	
	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	public int getStreamId() {
		return streamId;
	}
	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}
	public String getEntityIdent() {
		return entityIdent;
	}
	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public double getStreamVersion() {
		return streamVersion;
	}
	public void setStreamVersion(double streamVersion) {
		this.streamVersion = streamVersion;
	}
	public List<EntityVarStats> getVarStats() {
		return varStats;
	}
	public void setVarStats(List<EntityVarStats> varStats) {
		this.varStats = varStats;
	}
	
	
	

}
