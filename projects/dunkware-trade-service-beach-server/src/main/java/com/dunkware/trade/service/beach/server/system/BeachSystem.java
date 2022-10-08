package com.dunkware.trade.service.beach.server.system;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachSession;

/**
 * Yeah here we are baby 
 * @author duncan krebs
 *
 */
public interface BeachSystem {

	
	// going to have one session running at a time 
	// schedule controller on this level class 
	
	// we need to connect a System implementatiuon to get a resume/start/liquidate
	

	/**
	 * Returns the beach broker account that trades are executed through
	 * @return
	 */
	BeachAccount getAccount();
	
	/**
	 * Returns the system type 
	 * @return
	 */
	SystemType getSystemType();
	
	/**
	 * Return the active trading session if exists. 
	 * @return
	 * @throws Exception
	 */
	BeachSession getSession() throws Exception; 
	
	/**
	 * Starts a new trading session if one is not already started. 
	 * @return
	 * @throws Exception
	 */
	BeachSession startSession() throws Exception; 
	
	
	/**
	 * Updates the system type which will take effect in the next
	 * trading session started, not active. 
	 * @param type
	 * @throws Exception
	 */
	void saveType(SystemType type) throws Exception; 
	
	
	/**
	 * Returns the system spec. this needs to notify event listeners. 
	 * @return
	 */
	//BeachSystemSpec getSpec();

	/**
	 * returns true if in beach session
	 * @return
	 */
	public boolean inSession();
	
}
