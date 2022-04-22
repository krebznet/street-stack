package com.dunkware.net.cluster.json.node;

public class ClusterNodeUpdate {
	
	private String node; 
	private ClusterNodeState state; 
	private ClusterNodeStats stats;
	
	public ClusterNodeState getState() {
		return state;
	}
	public void setState(ClusterNodeState state) {
		this.state = state;
	}
	public ClusterNodeStats getStats() {
		return stats;
	}
	public void setStats(ClusterNodeStats stats) {
		this.stats = stats;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	
	
	
	

}
