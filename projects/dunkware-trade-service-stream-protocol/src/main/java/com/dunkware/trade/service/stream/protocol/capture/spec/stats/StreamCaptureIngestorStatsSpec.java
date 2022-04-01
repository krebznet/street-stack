package com.dunkware.trade.service.stream.protocol.capture.spec.stats;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.trade.service.stream.protocol.cluster.spec.ClusterNodeStatsSpec;

public class StreamCaptureIngestorStatsSpec {
	
	
	private ClusterNodeStatsSpec node; 
	private long ingestCount; 
	private DTime lastIngest;
	
	public ClusterNodeStatsSpec getNode() {
		return node;
	}
	public void setNode(ClusterNodeStatsSpec node) {
		this.node = node;
	}
	public long getIngestCount() {
		return ingestCount;
	}
	public void setIngestCount(long ingestCount) {
		this.ingestCount = ingestCount;
	}
	public DTime getLastIngest() {
		return lastIngest;
	}
	public void setLastIngest(DTime lastIngest) {
		this.lastIngest = lastIngest;
	} 
	
	

}
