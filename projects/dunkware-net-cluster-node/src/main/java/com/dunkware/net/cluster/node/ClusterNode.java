package com.dunkware.net.cluster.node;

import com.dunkware.common.util.dtime.DDateTime;

public interface ClusterNode {

	String getId();
	
	DDateTime getStartTime();

	ClusterNodeType getType();
	
	
}
