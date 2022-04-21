package com.dunkware.net.cluster.json.node;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.executor.DExecutorStats;
import com.dunkware.net.cluster.json.job.ClusterJobStats;

public class ClusterNodeStats {

	private ClusterNodeType type;
	private String id; 
	private DDateTime start; 
	private ClusterNodeState state;
	private List<ClusterJobStats> runningJobs = new ArrayList<ClusterJobStats>();
	private String httpEndpoint; 
	private String grpcEndpoint;
	private DExecutorStats executorStats;
	private int runningJobCount;
	
	public ClusterNodeType getType() {
		return type;
	}
	public void setType(ClusterNodeType type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DDateTime getStart() {
		return start;
	}
	public void setStart(DDateTime start) {
		this.start = start;
	}
	public ClusterNodeState getState() {
		return state;
	}
	public void setState(ClusterNodeState state) {
		this.state = state;
	}
	public List<ClusterJobStats> getRunningJobs() {
		return runningJobs;
	}
	public void setRunningJobs(List<ClusterJobStats> runningJobs) {
		this.runningJobs = runningJobs;
	}
	public String getHttpEndpoint() {
		return httpEndpoint;
	}
	public void setHttpEndpoint(String httpEndpoint) {
		this.httpEndpoint = httpEndpoint;
	}
	public String getGrpcEndpoint() {
		return grpcEndpoint;
	}
	public void setGrpcEndpoint(String grpcEndpoint) {
		this.grpcEndpoint = grpcEndpoint;
	}
	public DExecutorStats getExecutorStats() {
		return executorStats;
	}
	public void setExecutorStats(DExecutorStats executorStats) {
		this.executorStats = executorStats;
	}
	public int getRunningJobCount() {
		return runningJobCount;
	}
	public void setRunningJobCount(int runningJobCount) {
		this.runningJobCount = runningJobCount;
	}
	
	
	
	
	
	
	
	
	
	
}
