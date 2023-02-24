package com.dunkware.trade.service.stream.server.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.dunkware.trade.service.stream.json.controller.session.StreamSessionState;

@Entity(name = "stream_session")
public class StreamSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private LocalDateTime startingTime; 
	private int nodeCount; 
	private LocalDateTime startDateTime; 
	private LocalDateTime stopDateTime;
	
	private LocalDate date;
	
	@ManyToOne
	private StreamVersionEntity version;
	private double versionValue;
	private String streamName; 
	private int tickerCount; 
	private int nodeStartFailures = 0;

	private int problemCount;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true, fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private List<StreamSessionProblemEntity> problems = new ArrayList<StreamSessionProblemEntity>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private List<StreamSessionEntEntity> entities = new ArrayList<StreamSessionEntEntity>();
	
	
	@Column(name = "signal_count")
	private long signalCount = 0;
	
	@Column(name = "snapshot_count")
	private long snapshotCount = 0;
	
	@Column(name = "entity_count")
	private long entityCount = 0;

	
	@Transient
	private StreamSessionState state;
	
	@ManyToOne
	private StreamEntity stream;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public StreamEntity getStream() {
		return stream;
	}

	public void setStream(StreamEntity stream) {
		this.stream = stream;
	}

	public StreamSessionState getState() {
		return state;
	}

	public void setState(StreamSessionState state) {
		this.state = state;
	}

	public double getVersionValue() {
		return versionValue;
	}

	public void setVersionValue(double versionValue) {
		this.versionValue = versionValue;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public int getTickerCount() {
		return tickerCount;
	}

	public void setTickerCount(int tickerCount) {
		this.tickerCount = tickerCount;
	}

	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public StreamVersionEntity getVersion() {
		return version;
	}

	public void setVersion(StreamVersionEntity version) {
		this.version = version;
	}

	public int getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(int problemCount) {
		this.problemCount = problemCount;
	}

	public List<StreamSessionProblemEntity> getProblems() {
		return problems;
	}

	public void setProblems(List<StreamSessionProblemEntity> problems) {
		this.problems = problems;
	}

	public int getNodeStartFailures() {
		return nodeStartFailures;
	}

	public void setNodeStartFailures(int nodeStartFailures) {
		this.nodeStartFailures = nodeStartFailures;
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

	public long getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(long entityCount) {
		this.entityCount = entityCount;
	}

	public List<StreamSessionEntEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<StreamSessionEntEntity> entities) {
		this.entities = entities;
	}
	
	
	
	

	
	
	
	
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
