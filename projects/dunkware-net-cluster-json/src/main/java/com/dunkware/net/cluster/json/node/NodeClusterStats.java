package com.dunkware.net.cluster.json.node;

import java.util.ArrayList;
import java.util.List;

public class NodeClusterStats {
	
	private List<ClusterNodeStats> nodes = new ArrayList<ClusterNodeStats>();

	public List<ClusterNodeStats> getNodes() {
		return nodes;
	}

	public void setNodes(List<ClusterNodeStats> nodes) {
		this.nodes = nodes;
	}

	
}
