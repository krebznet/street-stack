package com.dunkware.trade.service.stream.json.controller.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StreamSpec {
	
	private String kafkaBrokers; 
	private String kafkaSignalTopic; 
	private String kafkaSnapshotTopic;
	private String streamIdentifier; 
	//private StreamScript script; 
	private String days; 
	private String timeZone; 
	private LocalTime startTime; 
	private LocalTime stopTime; 
	private double version; 
	private List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public List<StreamEntitySpec> getEntities() {
		return entities;
	}
	public void setEntities(List<StreamEntitySpec> entities) {
		this.entities = entities;
	}
	
	

}
