package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

import com.dunkware.xstream.model.signal.XStreamSignal;

public class WorkerSignalNotify {
	
	private String node; 
	private XStreamSignal signal;
	private String stream;
	
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public XStreamSignal getSignal() {
		return signal;
	}
	public void setSignal(XStreamSignal signal) {
		this.signal = signal;
	}
	
	public String getStream() {
		return stream;
	}
	
	public void setStream(String stream) {
		this.stream = stream;
	}

	
	

}
