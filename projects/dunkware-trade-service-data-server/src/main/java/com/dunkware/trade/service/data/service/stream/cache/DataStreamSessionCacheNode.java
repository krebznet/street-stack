package com.dunkware.trade.service.data.service.stream.cache;

import com.dunkware.net.cluster.node.ClusterNode;

public interface DataStreamSessionCacheNode {
	
	public void start(DataStreamSessionCacheController controller, ClusterNode node) throws Exception;
	
	public void stop();
	
	

}
