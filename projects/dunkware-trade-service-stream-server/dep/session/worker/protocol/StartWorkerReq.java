package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

import com.dunkware.xstream.model.XStreamBundle;

public class StartWorkerReq {
	
	private String streamName; 
	private XStreamBundle streamBundle;
	
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public XStreamBundle getStreamBundle() {
		return streamBundle;
	}
	public void setStreamBundle(XStreamBundle streamBundle) {
		this.streamBundle = streamBundle;
	}
	
	
	

}
