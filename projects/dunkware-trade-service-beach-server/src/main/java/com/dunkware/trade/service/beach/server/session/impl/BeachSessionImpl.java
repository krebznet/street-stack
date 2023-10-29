package com.dunkware.trade.service.beach.server.session.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.model.trade.BeachTradeModel;
import com.dunkware.trade.service.beach.server.entity.BeachSessionEntity;
import com.dunkware.trade.service.beach.server.session.BeachSession;
import com.dunkware.trade.service.beach.server.session.BeachSessionBean;
import com.dunkware.trade.service.beach.server.session.BeachSessionException;
import com.dunkware.trade.service.beach.server.session.BeachSessionOrderBean;
import com.dunkware.trade.service.beach.server.session.BeachSessionStatus;
import com.dunkware.trade.service.beach.server.session.BeachSessionTradeBean;
import com.dunkware.trade.service.beach.server.system.BeachSystem;

import ca.odell.glazedlists.ObservableElementList;

public class BeachSessionImpl implements BeachSession {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachSession");
	
	private BeachSystem system; 
	
	private BeachSessionEntity entity; 
	private BeachSessionStatus status; 
	private BeachSessionBean bean; 
	
	private ObservableElementList<BeachSessionTradeBean> tradeBeans;
	private ObservableElementList<BeachSessionOrderBean> orderBeans; 
	
	private DEventNode eventNode; 
	
	
	
	public BeachSessionImpl() { 
		bean = new BeachSessionBean();
		bean.setStatus("Loading");;
	}

	@Override
	public void start(BeachSystem system) throws BeachSessionException {
		this.system = system; 
		this.eventNode = system.getEventNode().createChild(this);
		BeachSystemModel model = system.getModel();
		// need to create strategies; 
		// need reference to trade allocator
		
		//this.system.get
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitTrade(BeachTradeModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObservableElementList<BeachSessionTradeBean> getTradeBeans() {
		return tradeBeans; 
	}

	@Override
	public ObservableElementList<BeachSessionOrderBean> getOrderBeans() {
		return orderBeans; 
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode; 
	}

	@Override
	public BeachSystem getSystem() {
		return system; 
	}

}
