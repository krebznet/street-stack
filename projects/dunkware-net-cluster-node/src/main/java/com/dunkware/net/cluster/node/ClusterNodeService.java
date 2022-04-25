package com.dunkware.net.cluster.node;

import java.util.List;

public interface ClusterNodeService {
	
	public List<ClusterNode> getAvailableWorkerNodes();
	
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException;

	public int getAvailableWorkerCount();
}
