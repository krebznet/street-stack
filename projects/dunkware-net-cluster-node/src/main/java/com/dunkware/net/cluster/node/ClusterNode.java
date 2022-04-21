package com.dunkware.net.cluster.node;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;

import io.grpc.Channel;

public interface ClusterNode {

	String getId();
	
	DDateTime getStartTime();

	ClusterNodeType getType();
	
	boolean offline();
	
	int runningJobCount();
	
	ClusterNodeStats getStats();
	
	public Object jsonPost(String path, Object request, Class response); 
	
	public void jsonPostVoid(String path, Object request); 
	
	public Object jsonPost(String path);
	
	public Object jsonGet(String path, Class response);
	
	public Object jsonGet(String path);
	
	public void jsonGetVoid(String path);
	
	public String httpGet(String path); 
	
	public String httpPost(String path);
	
	public Channel grpcChannel();

}
