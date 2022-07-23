package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.List;

import com.dunkware.trade.service.stream.server.controller.StreamController;

public interface SessionContainer {

	
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
	
	
	public StreamController getStream(); 
}
