package com.dunkware.trade.service.beach.server.session;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.server.system.BeachSystem;

import ca.odell.glazedlists.ObservableElementList;

/**
 * Okay this is an actuall live trading session  
 * @author duncankrebs
 *
 */
public interface BeachSession {
	
	public void start(BeachSystem system) throws BeachSessionException; 
	
	public void dispose(); 

	public void submitTrade(BeachTradeModel model);
	
	public ObservableElementList<BeachSessionTradeBean> getTradeBeans();
	
	public ObservableElementList<BeachSessionOrderBean> getOrderBeans();
	
	public DEventNode getEventNode();
	
	public BeachSystem getSystem();
	
	
	
	
	
	
	
	
	
}
