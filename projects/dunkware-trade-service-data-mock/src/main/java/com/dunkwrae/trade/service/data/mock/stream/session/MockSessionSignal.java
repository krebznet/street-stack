package com.dunkwrae.trade.service.data.mock.stream.session;

import java.time.LocalTime;

import com.dunkwrae.trade.service.data.mock.stream.MockStreamSignalType;

public class MockSessionSignal {
	
	private MockStreamSignalType type;
	private MockSessionEntity entity; 
	private LocalTime time; 
	
	public MockSessionSignal(MockSessionEntity entity, LocalTime time, MockStreamSignalType type) { 
		this.entity = entity;
		this.time = time;
		this.type = type;
	}

	public MockStreamSignalType getType() {
		return type;
	}

	public void setType(MockStreamSignalType type) {
		this.type = type;
	}

	public MockSessionEntity getEntity() {
		return entity;
	}

	public void setEntity(MockSessionEntity entity) {
		this.entity = entity;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
	
	

}
