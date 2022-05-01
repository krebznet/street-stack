package com.dunkware.trade.service.data.service.stream.session.cache.core;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.trade.service.data.service.stream.session.cache.DataStreamSessionCache;
import com.dunkware.trade.service.data.service.stream.session.cache.DataStreamSessionCacheNode;

import io.grpc.ManagedChannel;

public class DataStreamSessionCacheNodeImpl implements DataStreamSessionCacheNode  {

	@Override
	public void start(DataStreamSessionCache controller, ClusterNode node) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void routeEvent(GStreamEvent event) {
		// TODO Auto-generated method stub
		// 
	}

	@Override
	public ManagedChannel getChannel() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
