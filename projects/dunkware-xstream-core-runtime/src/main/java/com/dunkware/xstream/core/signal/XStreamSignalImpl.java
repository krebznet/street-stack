package com.dunkware.xstream.core.signal;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamSignal;
import com.dunkware.xstream.model.signal.StreamEntitySignal;

public class XStreamSignalImpl implements XStreamSignal {

	private XStreamEntity entity; 
	private StreamEntitySignal signal;
	
	public XStreamEntity getEntity() {
		return entity;
	}
	public void setEntity(XStreamEntity entity) {
		this.entity = entity;
	}
	public StreamEntitySignal getSignal() {
		return signal;
	}
	public void setSignal(StreamEntitySignal signal) {
		this.signal = signal;
	} 
	
}
