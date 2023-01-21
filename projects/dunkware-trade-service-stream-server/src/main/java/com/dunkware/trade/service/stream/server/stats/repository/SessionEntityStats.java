package com.dunkware.trade.service.stream.server.stats.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class SessionEntityStats {
	
	@Id
	private long id; 
	
	private long sessionId; 
	private long streamId; 
	private String streamIdent; 
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	private List<SessionEntityVarStats> varStats = new ArrayList<SessionEntityVarStats>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public long getStreamId() {
		return streamId;
	}

	public void setStreamId(long streamId) {
		this.streamId = streamId;
	}

	public String getStreamIdent() {
		return streamIdent;
	}

	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public List<SessionEntityVarStats> getVarStats() {
		return varStats;
	}

	public void setVarStats(List<SessionEntityVarStats> varStats) {
		this.varStats = varStats;
	}
	
	
	
	
	

}
