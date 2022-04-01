package com.dunkware.trade.service.stream.protocol.cluster;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.service.stream.protocol.cluster.spec.ClusterNodeStatsSpec;

public class ClusterStatusResp {
	
	private String status; 
	private String error; 
	
	private int nodeCount; 
	private int greeNodeCount;
	private int yellowNodeCount;
	private int redNodeCount;
	
	private List<ClusterNodeStatsSpec> nodes = new ArrayList<ClusterNodeStatsSpec>();

	public ClusterStatusResp() { 
		
	}
	
	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public List<ClusterNodeStatsSpec> getNodes() {
		return nodes;
	}

	public void setNodes(List<ClusterNodeStatsSpec> nodes) {
		this.nodes = nodes;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getGreeNodeCount() {
		return greeNodeCount;
	}

	public void setGreeNodeCount(int greeNodeCount) {
		this.greeNodeCount = greeNodeCount;
	}

	public int getYellowNodeCount() {
		return yellowNodeCount;
	}

	public void setYellowNodeCount(int yellowNodeCount) {
		this.yellowNodeCount = yellowNodeCount;
	}

	public int getRedNodeCount() {
		return redNodeCount;
	}

	public void setRedNodeCount(int redNodeCount) {
		this.redNodeCount = redNodeCount;
	}
	
	

	
}
