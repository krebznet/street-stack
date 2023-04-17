package com.dunkware.trade.service.stream.server.stats;

import java.util.Collection;

import com.dunkware.trade.service.stream.server.controller.StreamController;

/**
 * Stats Cache for a stream 
 * @author Duncan Krebs 
 *
 */
public interface StreamStats {

	/**
	 * gets the stream 
	 * @return
	 */
	StreamController getStream(); 
	
	/**
	 * Returns the aggregation of 
	 * @return
	 */
	StreamEntityStats getEntity(String ident) throws Exception;
	
	/**
	 * Returns all the stream entity stats. 
	 * @return
	 */
	Collection<StreamEntityStats> getEntities();
}
