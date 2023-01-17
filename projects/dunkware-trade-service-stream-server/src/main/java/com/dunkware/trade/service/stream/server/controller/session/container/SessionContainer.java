package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.List;

import com.dunkware.trade.service.stream.server.controller.StreamController;

public interface SessionContainer {

	
	/**
	 * Starts the container from a stream controller 
	 * @param controller
	 * @throws Exception
	 */
	public void start(StreamController controller) throws Exception;
	
	/**
	 * Disposes a session container 
	 */
	void dispose();
	
	/**
	 * Returns the list of nodes right? 
	 * @return
	 */
	List<SessionContainerNode> getNodes();
	
	/**
	 * returns the stream 
	 * @return
	 */
	public StreamController getStream(); 
	
	
	/**
	 * Returns the node that has the entity identifier exception if no node is found
	 * @param identifier
	 * @return
	 * @throws SessionContainerException
	 */
	public SessionContainerNode getEntityNode(String identifier) throws SessionContainerException; 
}

