package com.dunkware.trade.net.service.streamstats.server.service;

import java.util.Collection;

import com.dunkware.xstream.model.stats.StreamStatsPayload;

/**
 * Stats Cache for a stream 
 * @author Duncan Krebs 
 *
 */
public interface StreamStats {

	String getStreamIdent(); 
	
	/**
	 * Returns the aggregation of 
	 * @return
	 */
	StreamStatsEntity getEntity(String ident) throws Exception;
	
	/**
	 * Returns true or false if there is a stats entity 
	 * @param ident
	 * @return
	 */
	boolean entityExists(String ident);
	
	/**
	 * Returns all the stream entity stats. 
	 * @return
	 */
	Collection<StreamStatsEntity> getEntities();
	
	/**
	 * This comes from a running stream worker node that has the
	 * stream session statistics, here we need to persist and update our 
	 * cache
	 * @param payload
	 */
	void payload(StreamStatsPayload payload); 
	
	
}
