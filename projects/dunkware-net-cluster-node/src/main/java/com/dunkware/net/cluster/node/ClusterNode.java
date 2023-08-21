package com.dunkware.net.cluster.node;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.net.cluster.json.node.ClusterNodeState;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.json.node.ClusterNodeType;
import com.dunkware.spring.messaging.message.DunkNetMessage;

public interface ClusterNode {

	String getId();
	
	DDateTime getStartTime();

	ClusterNodeType getType();
	
	ClusterNodeState getState();
	
	public boolean isAvailable();
	
	int runningJobCount();
	
	ClusterNodeStats getStats();
	
	public Object jsonPost(String path, Object request, Class response) throws ClusterNodeException;
	
	public Object jsonPostSerializedRequest(String path, Object request, Class response) throws ClusterNodeException;
	
	public void jsonPostVoid(String path, Object request) throws ClusterNodeException;
	
	public Object jsonPost(String path) throws ClusterNodeException;
	
	public Object jsonGet(String path, Class response) throws ClusterNodeException;
	
	public void jsonGetVoid(String path) throws ClusterNodeException;
	
	public String httpGet(String path) throws ClusterNodeException;
	
	public String httpPost(String path) throws ClusterNodeException;;
	
	public String buildHttpURL(String path);
	
	public String getException();
	
	
	public void sendMessage(DunkNetMessage message) throws ClusterNodeException;
	
	public DunkNetMessage requestReply(DunkNetMessage message) throws ClusterNodeException;
	

}
