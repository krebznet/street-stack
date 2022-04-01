package com.dunkware.trade.service.stream.server.cluster;

public interface ClusterService {

	public ClusterNode[] getGreeNodes();

	public ClusterNode[] getGreeNodes(String...profiles);
	
	public ClusterNode[] getYellowNodes();
	
	public ClusterNode[] getRedNodes();
	
	public ClusterNode[] getNodes();
	
	public ClusterNode getNode(String id) throws ClusterException;
	
	public int getNodeCount();
	
	public boolean nodeExists(String id);
}
 