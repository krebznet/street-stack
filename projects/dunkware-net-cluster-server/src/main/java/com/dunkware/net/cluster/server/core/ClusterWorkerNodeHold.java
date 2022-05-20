package com.dunkware.net.cluster.server.core;

import java.util.ArrayList;
import java.util.List;

public class ClusterWorkerNodeHold {

	private List<ClusterNode> nodes = new ArrayList<ClusterNode>();
	private String owner;
	
	public List<ClusterNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<ClusterNode> nodes) {
		this.nodes = nodes;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	} 
	
	
}
