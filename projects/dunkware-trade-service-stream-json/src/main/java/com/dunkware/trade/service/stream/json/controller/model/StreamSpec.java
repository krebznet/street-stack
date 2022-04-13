package com.dunkware.trade.service.stream.json.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.model.script.StreamScript;

public class StreamSpec {
	
	private String kafkaBrokers; 
	private String kafkaSignalTopic; 
	private String kafkaSnapshotTopic;
	private String streamIdentifier; 
	private StreamScript script; 
	private String days; 
	private DTimeZone timeZone; 
	private DTime startTime; 
	private DTime stopTime; 
	private double version; 
	private List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	
	public StreamScript getScript() {
		return script;
	}
	public void setScript(StreamScript script) {
		this.script = script;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	public DTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}
	public DTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(DTime stopTime) {
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
