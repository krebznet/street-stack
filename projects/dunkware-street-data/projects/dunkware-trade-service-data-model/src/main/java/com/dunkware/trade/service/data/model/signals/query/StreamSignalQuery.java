package com.dunkware.trade.service.data.model.signals.query;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;

public class StreamSignalQuery {
	
	
	private List<Integer> entities = new ArrayList<Integer>();
	private List<Integer> signalTypes = new ArrayList<Integer>();
	private LocalDateTime timeRangeStart = null;
	private LocalDateTime timeRangeEnd = null;
	private boolean timeRangeSession = false; 
	private int limit = 500; 
	
	public static void main(String[] args) {
		StreamSignalQuery  q = new StreamSignalQuery();
		q.getSignalTypes().add(4);
		q.getEntities().add(4);
		q.timeRangeSession = true;
		try {
			System.out.println(DJson.serializePretty(q));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private String stream;
	
	public List<Integer> getEntities() {
		return entities;
	}
	
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	
	public String getStream() {
		return stream;
	}
	
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	public LocalDateTime getTimeRangeStart() {
		return timeRangeStart;
	}
	
	public void setTimeRangeStart(LocalDateTime timeRangeStart) {
		this.timeRangeStart = timeRangeStart;
	}
	
	public LocalDateTime getTimeRangeEnd() {
		return timeRangeEnd;
	}
	public void setTimeRangeEnd(LocalDateTime timeRangeEnd) {
		this.timeRangeEnd = timeRangeEnd;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isTimeRangeSession() {
		return timeRangeSession;
	}

	public void setTimeRangeSession(boolean timeRangeSession) {
		this.timeRangeSession = timeRangeSession;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	

}
