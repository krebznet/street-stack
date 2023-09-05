package com.dunkware.xstream.core;

import java.time.LocalTime;

import com.dunkware.xstream.api.XStreamRuntimeError;

public class XStreamRuntimeErrorImpl implements XStreamRuntimeError {

	private String type; 
	private LocalTime time; 
	private String message; 
	
	public XStreamRuntimeErrorImpl() { 
		
	}
	
	public XStreamRuntimeErrorImpl(String type, String message, LocalTime time) { 
		this.type = type; 
		this.message = message; 
		this.time = time; 
	}
	
	@Override
	public String getType() {
		return type; 
		
	}

	@Override
	public LocalTime getTime() {
		return time; 
	}

	@Override
	public String getMessage() {
		return message;
	}

	
	
}
