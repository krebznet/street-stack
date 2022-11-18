package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;
import java.util.List;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.node.internal.ClusterConfig;

@Service
public interface Cluster {
	
	public DEventTree getEventTree();
	
	public DExecutor getExecutor();
	
	public String getNodeId();
	
	public LocalDateTime now();
	
	public ClusterNodeStats getStats();
	
	public ClusterConfig getConfig();
	
	public List<ClusterNode> getAvailableWorkerNodes();
	
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException;

	public ClusterNode getNode(String nodeId) throws ClusterNodeException;

	public Reflections getDunkwareReflections();
}
	
	
	
	
