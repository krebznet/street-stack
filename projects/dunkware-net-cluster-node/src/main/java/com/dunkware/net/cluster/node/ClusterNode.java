package com.dunkware.net.cluster.node;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;

import io.grpc.Channel;

public interface ClusterNode {

	String getId();
	
	DDateTime getStartTime();

	ClusterNodeType getType();
	
	ClusterNodeState getState();
	
	public boolean isAvailable();
	
	int runningJobCount();
	
	ClusterNodeStats getStats();
	
	public Object jsonPost(String path, Object request, Class response) throws ClusterNodeException;
	
	public void jsonPostVoid(String path, Object request) throws ClusterNodeException;
	
	public Object jsonPost(String path) throws ClusterNodeException;
	
	public Object jsonGet(String path, Class response) throws ClusterNodeException;
	
	public void jsonGetVoid(String path) throws ClusterNodeException;
	
	public String httpGet(String path) throws ClusterNodeException;
	
	public String httpPost(String path) throws ClusterNodeException;;
	
	public Channel grpcChannel() throws ClusterNodeException;;
	
	public String getException();

}
