package com.dunkware.net.cluster.json.node;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClusterNodeStatsList {
	
	private List<ClusterNodeStats> nodes = new ArrayList<ClusterNodeStats>();

	public List<ClusterNodeStats> getNodes() {
		return nodes;
	}

	public void setNodes(List<ClusterNodeStats> nodes) {
		this.nodes = nodes;
	}

	
}
