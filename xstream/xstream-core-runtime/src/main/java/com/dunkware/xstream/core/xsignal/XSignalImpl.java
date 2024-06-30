package com.dunkware.xstream.core.xsignal;

import java.time.LocalDateTime;
import java.util.Map;

import com.dunkware.xstream.api.XSignal;
import com.dunkware.xstream.api.XSignalType;
import com.dunkware.xstream.api.XStreamEntity;

public class XSignalImpl implements XSignal  {
	
	private XSignalType type; 
	private XStreamEntity entity; 
	private LocalDateTime timestamp; 
	private Map<Integer,Number> numericVars; 
	

	public XSignalImpl(XStreamEntity entity,XSignalType type, LocalDateTime timestamp, Map<Integer,Number> numericVars) { 
		this.type = type; 
		this.entity = entity; 
		this.timestamp = timestamp;
		this.numericVars = numericVars;
	}
	
	@Override
	public XStreamEntity getEntity() {
		return entity;
	}

	@Override
	public LocalDateTime getTimeStamp() {
		return timestamp;
	}

	@Override
	public XSignalType getType() {
		return type;
	}

	@Override
	public Map<Integer, Number> getNumericVariables() {
		return numericVars;
	}
	
	

}
