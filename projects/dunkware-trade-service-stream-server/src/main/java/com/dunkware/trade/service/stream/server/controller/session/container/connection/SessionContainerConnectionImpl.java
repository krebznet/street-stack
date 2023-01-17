package com.dunkware.trade.service.stream.server.controller.session.container.connection;

import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerConnection;

public class SessionContainerConnectionImpl implements SessionContainerConnection {

	private Channel channel; 
	private SessionContainer container;
	
	
	@Autowired
	private Cluster cluster; 
	
	public void start(String brokers, String yourTopic, String clientTopic, SessionContainer container) throws Exception { 
		// "ContainerClient" type 
	}
	
	@Override
	public Channel getChannel() {
		return channel; 
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
