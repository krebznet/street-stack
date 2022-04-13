package com.dunkware.trade.service.stream.server.cluster;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.stream.json.cluster.spec.ClusterNodeStatsSpec;
import com.dunkware.trade.service.stream.json.cluster.spec.ClusterNodeStatus;

public interface ClusterNode {
	
	String getEndpoint();
	
	String getEndpoint(String path);
	
	String getId();
	
	ClusterNodeStatus getStatus();
	
	DEventNode getEventNode();
	
	String[] getProfiles();
	
	boolean hasProfile(String profile);
	
	boolean hasProfiles(String[] profiles);
	
	ClusterNodeStatsSpec getStats();
	
	Object postReqResp(String path, Object req, Class respType) throws Exception;
	
	Object reqResp(String path, Class respType) throws Exception;
	
	String reqResp(String path) throws Exception;
	
	
}
