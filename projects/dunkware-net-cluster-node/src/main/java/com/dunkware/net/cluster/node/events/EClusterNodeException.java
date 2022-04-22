package com.dunkware.net.cluster.node.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.net.cluster.node.ClusterNode;

public class EClusterNodeException extends DEvent {

	private ClusterNode node;
	
	public EClusterNodeException(ClusterNode node) { 
		this.node = node;
	}
	
	public ClusterNode getNode() { 
		return node;
	}
}
