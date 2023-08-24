package com.dunkware.trade.service.stream.json.worker.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.xstream.model.signal.XStreamSignalModel;
import com.dunkware.xstream.model.stats.EntityStatsSessions;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerStartReq {

	private XStreamBundle streamBundle; 
	private String stream; 
	private int workerId; 
	private String sessionId; 
	private List<XStreamSignalModel> signals = new ArrayList<XStreamSignalModel>();
	private String nodeId;
	
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

	

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public List<XStreamSignalModel> getSignals() {
		return signals;
	}

	public void setSignals(List<XStreamSignalModel> signals) {
		this.signals = signals;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	

	
	
}
