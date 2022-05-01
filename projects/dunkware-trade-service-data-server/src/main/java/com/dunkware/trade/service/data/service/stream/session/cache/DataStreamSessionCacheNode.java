package com.dunkware.trade.service.data.service.stream.session.cache;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.stream.GStreamEvent;

import io.grpc.ManagedChannel;

public interface DataStreamSessionCacheNode {
	
	public void start(DataStreamSessionCache controller, ClusterNode node) throws Exception;
	
	public void stop();
	
	public void routeEvent(GStreamEvent event);
	
	public ManagedChannel getChannel() throws Exception;
	
	

}
