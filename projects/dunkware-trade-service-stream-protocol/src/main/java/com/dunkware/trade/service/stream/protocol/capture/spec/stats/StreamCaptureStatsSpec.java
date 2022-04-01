package com.dunkware.trade.service.stream.protocol.capture.spec.stats;

import java.util.ArrayList;
import java.util.List;

public class StreamCaptureStatsSpec {
	
	private int partitionCount; 
	private int ingestorCount; 
	private int ingestCount; 
	private List<StreamCaptureIngestorStatsSpec> ingestors = new ArrayList<StreamCaptureIngestorStatsSpec>();
	
	public int getPartitionCount() {
		return partitionCount;
	}
	public void setPartitionCount(int partitionCount) {
		this.partitionCount = partitionCount;
	}
	public int getIngestorCount() {
		return ingestorCount;
	}
	public void setIngestorCount(int ingestorCount) {
		this.ingestorCount = ingestorCount;
	}
	public int getIngestCount() {
		return ingestCount;
	}
	public void setIngestCount(int ingestCount) {
		this.ingestCount = ingestCount;
	}
	public List<StreamCaptureIngestorStatsSpec> getIngestors() {
		return ingestors;
	}
	public void setIngestors(List<StreamCaptureIngestorStatsSpec> ingestors) {
		this.ingestors = ingestors;
	}
	
	
	
}
