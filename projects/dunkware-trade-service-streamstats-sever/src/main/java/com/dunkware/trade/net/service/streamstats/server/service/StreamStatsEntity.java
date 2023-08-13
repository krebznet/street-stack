package com.dunkware.trade.net.service.streamstats.server.service;

import java.time.LocalDate;

import com.dunkware.xstream.model.stats.EntityStatsAgg;
import com.dunkware.xstream.model.stats.EntityStatsSession;
import com.dunkware.xstream.model.stats.EntityStatsSessions;

public interface StreamStatsEntity {
	
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
	
	/**
	 * Adds a new EntityStatsSession to the session list done
	 * when new session stats are inserted into the database. 
	 * @param session
	 */
	void addSession(EntityStatsSession session); 
	
	/**
	 * Returns true if it has a session for the input date false otherwise
	 * @param date
	 * @return
	 */
	boolean sessionExists(LocalDate date);
	
	/**
	 * Returns a session by date exception if now found
	 * @param date
	 * @return
	 */
	EntityStatsSession getSession(LocalDate date) throws Exception;
	
	/**
	 * Okay this should resolve the highest value from a date
	 * @param date
	 * @param daysBack
	 * @return
	 * @throws StreamStatsException
	 */
	Number resolveVarRelativeHigh(LocalDate date, String varIdent, int daysBack) throws StreamStatsInternalException, StreamStatsResolveException; 
	
	


}
