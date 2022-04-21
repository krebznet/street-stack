package com.dunkware.net.cluster.node;

public interface ClusterJobRunner {

	public void startJob(ClusterJob job) throws Exception;
	
	public void terminate();
}
