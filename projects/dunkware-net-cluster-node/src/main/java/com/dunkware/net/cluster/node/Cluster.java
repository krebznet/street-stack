package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.node.internal.ClusterConfig;

import io.grpc.ManagedChannel;

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
	
	public ClusterNodeService getNodeSevice();
	
	public ClusterNodeStats getStats();
	
	public ClusterConfig getConfig();
	
	public ManagedChannel getServerChannel();
}
	
	
	
	
