package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;
import java.util.List;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.node.internal.ClusterConfig;
import com.dunkware.spring.message.Message;

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
	
	/**
	 * Okay Maybe this can round robin or load balance service calls 
	 * @param payload
	 * @return
	 * @throws ClusterNodeException
	 */
	public Message clusterService(Object payload) throws ClusterNodeException;
}
// DunkCluster
// DunkNode -- 
// 	has service("ad") return 
// invoke service() - 

// DunkNode 

	
	
	
	
