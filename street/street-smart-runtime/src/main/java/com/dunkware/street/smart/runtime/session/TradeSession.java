package com.dunkware.street.smart.runtime.session;

import java.util.List;

import com.dunkware.utils.core.events.DunkEventNode;

//TODO: AVINASHANV-31 Trade Session
/**
 * More work to do what what will happen is a trade sesion will run on a broker 
 * account that can submit orders, we view this trade session as trades and we 
 * take the setup objects in a script trade bot model and convert them to trade validators
 * 
 */
public interface TradeSession {

	public List<TradeStrategy> getStrategies();

	public List<TradeValidator> getValidators();

	/**
	 * Notice this event node if a object registers itself 
	 * as a handler where its methods are search for handler 
	 * methods it can listen to all events down the trade sesion tree
	 * @return
	 */
	public DunkEventNode getEventNode();
	
	
	//TODO: AVINASHANV-35 Trade Session
	/**
	 * what happens is the tradeBot defined in script has a list of trade plays and setup constraints
	 * we will simply traverse the model and create a trade session instance and each trade play will
	 * be a runtime trade strategy and it will configure the strategy with the constraints exit and entry
	 * types and so there is no real engine for the trade bot it just adapts to the trade session. 
	 */

}
