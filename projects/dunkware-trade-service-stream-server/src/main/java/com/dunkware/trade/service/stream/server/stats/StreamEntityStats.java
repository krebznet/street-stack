package com.dunkware.trade.service.stream.server.stats;

import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsSessions;

public interface StreamEntityStats {
	
	/**
	 * returns the entity id. 
	 * @return
	 */
	int getId();
	
	/**
	 * Returns the entity identifier 
	 * @return
	 */
	String getIdent();
	
	/**
	 * Returns the number of sessions we have stats for 
	 * @return
	 */
	int getSessionCount();
	
	/**
	 * Returns the aggregation 
	 * @return
	 */
	EntityStatsAgg getAgg();
	
	/**
	 * Returns the list of entity stat sessions
	 * @return
	 */
	EntityStatsSessions getSessions(); 


}
