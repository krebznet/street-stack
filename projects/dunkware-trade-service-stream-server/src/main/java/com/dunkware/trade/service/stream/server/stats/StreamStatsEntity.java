package com.dunkware.trade.service.stream.server.stats;

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


}
