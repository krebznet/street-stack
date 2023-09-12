package com.dunkware.xstream.model.signal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class StreamEntitySignal {
	
	private int entity;
	private int id;
	private LocalDateTime dateTime;
	private ZoneId zoneId;
	
	private Map<Integer, Number> vars = new HashMap<Integer,Number>();


	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public Map<Integer, Number> getVars() {
		return vars;
	}

	public void setVars(Map<Integer, Number> vars) {
		this.vars = vars;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public ZoneId getZoneId() {
		return zoneId;
	}

	public void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}

}
