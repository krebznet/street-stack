package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.Set;

public interface SessionContainerService {

	
	/**
	 * Returns a stream container 
	 * @param streamIdentifier
	 * @return
	 * @throws Exception
	 */
	public SessionContainer getContainer(String streamIdentifier) throws Exception; 
	
	/**
	 * Returns the set of session container classes 
	 * @return
	 */
	public Set<Class<?>> getContainerExtensions();
}
