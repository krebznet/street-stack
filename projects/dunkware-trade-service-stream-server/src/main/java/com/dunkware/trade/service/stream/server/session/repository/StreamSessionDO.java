package com.dunkware.trade.service.stream.server.session.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionStatus;
import com.dunkware.trade.service.stream.server.controller.repository.StreamDO;
import com.dunkware.trade.service.stream.server.controller.repository.StreamVersionDO;

@Entity(name = "stream_session")
public class StreamSessionDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private LocalDateTime startingTime; 
	private int nodeCount; 
	private LocalDateTime startDateTime; 
	private LocalDateTime stopDateTime;
	
	private LocalDate date;
	@ManyToOne
	private StreamVersionDO version;
	private double versionValue;
	private String streamName; 
	private int tickerCount; 

	private int problemCount;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true, fetch = FetchType.EAGER)
	@JoinColumn(name = "session_id")
	private List<StreamSessionTickerDO> tickers = new ArrayList<StreamSessionTickerDO>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true, fetch = FetchType.EAGER)
	@JoinColumn(name = "session_id")
	private List<StreamSessionProblemDO> problems = new ArrayList<StreamSessionProblemDO>();
	
	@Transient
	private StreamSessionState state;
	
	@ManyToOne
	private StreamDO stream;

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

	public List<StreamSessionTickerDO> getTickers() {
		return tickers;
	}

	public void setTickers(List<StreamSessionTickerDO> tickers) {
		this.tickers = tickers;
	}

	public StreamDO getStream() {
		return stream;
	}

	public void setStream(StreamDO stream) {
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

	public StreamVersionDO getVersion() {
		return version;
	}

	public void setVersion(StreamVersionDO version) {
		this.version = version;
	}

	public int getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(int problemCount) {
		this.problemCount = problemCount;
	}

	public List<StreamSessionProblemDO> getProblems() {
		return problems;
	}

	public void setProblems(List<StreamSessionProblemDO> problems) {
		this.problems = problems;
	}
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
