package com.dunkware.net.cluster.node;

import com.dunkware.net.cluster.json.job.ClusterJobState;

public interface ClusterJob {

	ClusterJobRunner getRunner();
	
	void jobException(String error);
	
	void jobComplete();
	
	ClusterJobState getState();
	
}
