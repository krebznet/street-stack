package com.dunkware.net.cluster.node;

import java.util.List;

public interface ClusterNodeService {
	
	public void releaseWorkerNodes(String owner); 
	
	public List<ClusterNode> reserveWorkerNodes(String owner, int requested) throws Exception;
	
	public List<ClusterNode> getAvailableWorkerNodes();
	
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException;

	public int getAvailableWorkerCount();
	
	public ClusterNode getNode(String nodeId) throws ClusterNodeException;
}
