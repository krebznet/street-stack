package com.dunkware.trade.service.stream.json.worker.stream;

import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerStartReq {

	private XStreamBundle streamBundle; 
	private String stream; 
	private String workerId; 
	private String sessionId; 
	
	public StreamSessionWorkerStartReq() { 
	
	}

	public XStreamBundle getStreamBundle() {
		return streamBundle;
	}

	public void setStreamBundle(XStreamBundle streamBundle) {
		this.streamBundle = streamBundle;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
}
