package com.dunkware.net.cluster.json.node;

import com.dunkware.common.util.dtime.DDateTime;

public class ClusterNodeStats {

	private ClusterNodeType type;
	private String id; 
	private DDateTime start; 
	private ClusterNodeState state;
	
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
	
	
}
