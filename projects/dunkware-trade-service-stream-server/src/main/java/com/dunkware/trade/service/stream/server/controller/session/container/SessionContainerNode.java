package com.dunkware.trade.service.stream.server.controller.session.container;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.channel.Channel;

public interface SessionContainerNode {
	
	/**
	 * Returns The Channel 
	 * @return
	 */
	Channel getChannel();
	
	/**
	 * Returns the cluster node 
	 * @return
	 */
	ClusterNode getCluserNode();
	
	/**
	 * Disposes the ndoe 
	 */
	void dispose(); 

}
