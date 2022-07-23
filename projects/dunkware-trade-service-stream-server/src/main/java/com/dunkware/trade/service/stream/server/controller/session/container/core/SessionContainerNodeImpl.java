package com.dunkware.trade.service.stream.server.controller.session.container.core;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerNode;

public class SessionContainerNodeImpl implements SessionContainerNode {

	private Channel channel; 
	
	
	private ClusterNode node; 
	
	private SessionContainer sessionContainer; 
	
	
	public void start(ClusterNode node, SessionContainer container) throws Exception {
		this.node = node; 
		this.sessionContainer = container; 
		
		// rest api for channel request -> channel request
		// make that universal on all clusters 
		// so you can make a channel directly with a cluster node 
		// createChannel -> can pass in object payload -> have a 
		// channelInitializer right ? 
		// "EntityScannerChannel" -> ‰
		
	}
	
	
	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public ClusterNode getCluserNode() {
		return  node; 
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
