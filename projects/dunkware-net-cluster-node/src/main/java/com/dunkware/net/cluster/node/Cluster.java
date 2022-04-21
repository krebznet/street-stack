package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;

@Service
public interface Cluster {
	
	public DEventTree getEventTree();
	
	public DExecutor getExecutor();
	
	public String getNodeId();
	
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws Exception;
	
	public void pojoEvent(Object pojo) throws Exception;
	
	public LocalDateTime now();
	
	public void addComponent(Object component); 
	
	public void removeComponent(Object component);
	
}
