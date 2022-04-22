package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;

@Service
public interface Cluster {
	
	public DEventTree getEventTree();
	
	public DExecutor getExecutor();
	
	public String getNodeId();
	
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws ClusterNodeException;
	
	public void pojoEvent(Object pojo) throws ClusterNodeException;;
	
	public LocalDateTime now();
	
	public void addComponent(Object component); 
	
	public void removeComponent(Object component);
	
	public List<ClusterNode> getAvailableWorkerNodes();
	
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException;
	
	public int getAvailablWorkereNodeCount();
	
	public boolean nodeExists(String id);
	
	public ClusterNode getNode(String id) throws ClusterNodeException;
	
	public ClusterNodeStats getStats();
	
}
	
	
	
	
