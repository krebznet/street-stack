package com.dunkware.trade.service.stream.server.controller.session.container;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.channel.Channel;
import com.dunkware.trade.service.stream.server.controller.StreamController;

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
	 * Disposes the node 
	 */
	void dispose(); 

	/**
	 * Returns true or false if entity is in the container 
	 * @param entity
	 * @return
	 */
	boolean hasEntity(String identifier);
	
	
	/**
	 * Adds an entity
	 * @param identifier
	 */
	public void addEntity(String identifier);
	
	/**
	 * Returns the session container 
	 * @return
	 */
	public SessionContainer getSessionContainer();
	
	/**
	 * Returns the stream 
	 * @return
	 */
	public StreamController getStream();
}
