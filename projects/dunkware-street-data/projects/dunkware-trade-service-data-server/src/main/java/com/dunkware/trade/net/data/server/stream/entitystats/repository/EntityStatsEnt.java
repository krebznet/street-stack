package com.dunkware.trade.net.data.server.stream.entitystats.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// data_entity_stat_file
// data_enttiy_stat_file_entity
// data_entity_stat_file_stattype
// Data_dentity_stat_file_signals
@Entity(name = "data_stream_entity_stats_session")
public class EntityStatsEnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String streamIdent; 
	private long streamId; 
	private long sessionId; 
	private LocalDate date; 
	private LocalDateTime startDateTime;
	private LocalDateTime stopDateTime; 
	private String sessionFile;
	private String sessionFileDirectory;
	private boolean exception = false;
	private String exceptionMessage = null; 
	private int insertCount; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	}
	public long getStreamId() {
		return streamId;
	}
	public void setStreamId(long streamId) {
		this.streamId = streamId;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getStopDateTime() {
		return stopDateTime;
	}
	public void setStopDateTime(LocalDateTime stopDateTime) {
		this.stopDateTime = stopDateTime;
	}
	public String getSessionFile() {
		return sessionFile;
	}
	public void setSessionFile(String sessionFile) {
		this.sessionFile = sessionFile;
	}
	public String getSessionFileDirectory() {
		return sessionFileDirectory;
	}
	public void setSessionFileDirectory(String sessionFileDirectory) {
		this.sessionFileDirectory = sessionFileDirectory;
	}
	public boolean isException() {
		return exception;
	}
	public void setException(boolean exception) {
		this.exception = exception;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public int getInsertCount() {
		return insertCount;
	}
	public void setInsertCount(int insertCount) {
		this.insertCount = insertCount;
	}
	
	
	
	
	
	
	
	
	

}
