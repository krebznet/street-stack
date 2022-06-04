package com.dunkware.net.cluster.node;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.node.internal.ClusterConfig;
import com.dunkware.net.core.service.NetCallRequest;
import com.dunkware.net.core.service.NetCallResponseCallback;
import com.dunkware.net.core.service.NetCallResponse;
import com.dunkware.net.core.service.NetChannelRequest;
import com.dunkware.net.core.service.NetChannelResponse;
import com.dunkware.net.core.service.NetChannelResponseCallback;
import com.dunkware.net.core.service.NetMessageHandler;

import io.grpc.ManagedChannel;

@Service
public interface Cluster {
	
	public DEventTree getEventTree();
	
	public DExecutor getExecutor();
	
	public String getNodeId();
	
	public ClusterJob startJob(ClusterJobRunner runner, String type, String name) throws ClusterNodeException;
	
	public void pojoEvent(Object pojo) throws ClusterNodeException;;
	
	public LocalDateTime now();
	
	public void addNetMessageHandler(NetMessageHandler handler);
	
	public void removeNetMessageHandler(NetMessageHandler handler);
	
	public void addComponent(Object component); 
	
	public void removeComponent(Object component);
	
	public ClusterNodeStats getStats();
	
	public ClusterConfig getConfig();
	
	public ManagedChannel getServerChannel();
	
	public ClusterNode getCallRequestNode(String endpoint) throws ClusterNodeException;
	
	public boolean hasCallNode(String endpoint);
	
	public boolean hasChannelNode(String endpoint);
	
	public ClusterNode getChannelRequestNode(String endpoint);
	
	public void netCall(NetCallRequest request, NetCallResponseCallback callback) throws ClusterNodeException;
	
	public void netChannel(NetChannelRequest request, NetChannelResponseCallback callback) throws ClusterNodeException;
	
	public void releaseWorkerNodes(String owner); 
	
	public List<ClusterNode> reserveWorkerNodes(String owner, int requested) throws Exception;
	
	public List<ClusterNode> getAvailableWorkerNodes();
	
	public List<ClusterNode> getAvailablWorkereNodes(int count) throws ClusterNodeException;

	public int getAvailableWorkerCount();
	
	public ClusterNode getNode(String nodeId) throws ClusterNodeException;
	
	
	
}
	
	
	
	
