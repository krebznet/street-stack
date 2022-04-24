package com.dunkware.trade.service.data.util.cache.session.core;

import java.time.LocalDateTime;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.util.cache.session.SessionEntity;
import com.dunkware.trade.service.data.util.cache.session.SessionEntityVar;
import com.dunkware.trade.service.data.util.cache.session.SessionEntityVarListener;

public class SessionEntityVarImpl implements SessionEntityVar {

	private SessionEntity entity; 
	private String identifier;
	private int id; 
	
	private LocalDateTime highTIme = null;
	private LocalDateTime lowTime = null;
	
	private Object highValue; 
	private Object lowValue;
	
	private Object value;
	
	// we should also know our data type
	
	public void start(SessionEntity entity, GEntitySnapshot snapshot) { 
		this.entity = entity;
		this.identifier = snapshot.getIdentifier();
		id = snapshot.getId();
		
	}
	
	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Object getHigh() {
		return highValue;
	}

	@Override
	public LocalDateTime getHighTime() {
		return highTIme;
	}

	@Override
	public Object getLow() {
		return lowValue;
	}

	@Override
	public LocalDateTime getLowTime() {
		return lowTime;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public SessionEntity getEntity() {
		return entity;
	}

	@Override
	public void varUpdate(Object value) {
		
	}

	@Override
	public void addListener(SessionEntityVarListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(SessionEntityVarListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	

}
