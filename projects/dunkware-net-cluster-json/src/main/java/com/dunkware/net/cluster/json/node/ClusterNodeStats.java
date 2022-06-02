package com.dunkware.net.cluster.json.node;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.executor.DExecutorStats;
import com.dunkware.net.cluster.json.job.ClusterJobStats;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClusterNodeStats {

	private ClusterNodeType type;
	private String id; 
	private String start; 
	private List<ClusterJobStats> runningJobs = new ArrayList<ClusterJobStats>();
	private String httpEndpoint; 
	private String grpcEndpoint;
	private int availableProcessors;
	private double systemLoadAverage;
	private double freeMemory;
	private double maxMemory;
	private double totalMemory;
	private DExecutorStats executorStats;
	private int runningJobCount;
	private List<ClusterNodeService> services = new ArrayList<ClusterNodeService>();

	
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
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public int getAvailableProcessors() {
		return availableProcessors;
	}
	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
	public double getSystemLoadAverage() {
		return systemLoadAverage;
	}
	public void setSystemLoadAverage(double systemLoadAverage) {
		this.systemLoadAverage = systemLoadAverage;
	}
	public double getFreeMemory() {
		return freeMemory;
	}
	public void setFreeMemory(double freeMemory) {
		this.freeMemory = freeMemory;
	}
	public double getMaxMemory() {
		return maxMemory;
	}
	public void setMaxMemory(double maxMemory) {
		this.maxMemory = maxMemory;
	}
	public double getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(double totalMemory) {
		this.totalMemory = totalMemory;
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
	public List<ClusterNodeService> getServices() {
		return services;
	}
	public void setServices(List<ClusterNodeService> services) {
		this.services = services;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
