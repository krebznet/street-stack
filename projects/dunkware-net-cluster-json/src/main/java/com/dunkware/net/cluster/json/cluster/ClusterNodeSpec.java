package com.dunkware.net.cluster.json.cluster;

import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;

public class ClusterNodeSpec {
	
	private String id;
	private ClusterNodeState state;
	private ClusterNodeType type;
	private ClusterNodeStats status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClusterNodeState getState() {
		return state;
	}

	public void setState(ClusterNodeState state) {
		this.state = state;
	}

	public ClusterNodeType getType() {
		return type;
	}

	public void setType(ClusterNodeType type) {
		this.type = type;
	}

	public ClusterNodeStats getStatus() {
		return status;
	}

	public void setStatus(ClusterNodeStats status) {
		this.status = status;
	} 
	
	
	
	

}
