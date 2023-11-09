package com.dunkware.trade.service.stream.json.worker.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionWorkerStartReq {

	private XStreamBundle streamBundle; 
	private String stream; 
	private long entitySessionId;
	private int numericId;
	private String workerId;
	private String sessionId; 
	private List<XStreamSignalType> signals = new ArrayList<XStreamSignalType>();
	private String nodeId;
	private String kafkaBrokers;
	private StreamDescriptor streamDescriptor;
	private Map<String,String> streamProperties = new HashMap<String,String>();
	public StreamSessionWorkerStartReq() { 
	
	}

	public XStreamBundle getStreamBundle() {
		return streamBundle;
	}
	
	
	public StreamDescriptor getStreamDescriptor() {
		return streamDescriptor;
	}

	public void setStreamDescriptor(StreamDescriptor streamDescriptor) {
		this.streamDescriptor = streamDescriptor;
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

	public int getNumericId() {
		return numericId;
	}

	public void setNumericId(int numericId) {
		this.numericId = numericId;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	

	public List<XStreamSignalType> getSignals() {
		return signals;
	}

	public void setSignals(List<XStreamSignalType> signals) {
		this.signals = signals;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}

	public Map<String, String> getStreamProperties() {
		return streamProperties;
	}

	public void setStreamProperties(Map<String, String> streamProperties) {
		this.streamProperties = streamProperties;
	}

	public long getEntitySessionId() {
		return entitySessionId;
	}

	public void setEntitySessionId(long entitySessionId) {
		this.entitySessionId = entitySessionId;
	}
	
	
	
	

	
	
	
	

	
	
}
