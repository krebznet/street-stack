package com.dunkware.trade.service.beach.server.system.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerService;
import com.dunkware.trade.service.beach.server.entity.BeachSystemEntity;
import com.dunkware.trade.service.beach.server.session.BeachSession;
import com.dunkware.trade.service.beach.server.session.BeachSessionException;
import com.dunkware.trade.service.beach.server.system.BeachSystem;
import com.dunkware.trade.service.beach.server.system.BeachSystemBean;
import com.dunkware.trade.service.beach.server.system.BeachSystemService;
import com.dunkware.trade.service.beach.server.system.BeachSystemStatus;

public class BeachSystemImpl implements BeachSystem  {
	
	@Autowired
	private BeachSystemService systemService; 
	@Autowired
	private BeachBrokerService brokerServiice; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachSystem");
	
	
	private DEventNode eventNode; 
	private BeachSystemBean bean; 
	private BeachSession session; 
	private BeachSystemStatus status = BeachSystemStatus.Loading; 
	
	private BeachSystemEntity entity; 
	
	public BeachSystemImpl() { 
		bean = new BeachSystemBean();
		bean.setStatus(status.name());
		
	}
	
	public void init(BeachSystemEntity entity) { 
		this.entity = entity; 
		
	}

	@Override
	public BeachSystemBean getBean() {
		return bean; 
	}

	@Override
	public BeachSystemEntity getEntity() {
		return entity; 
	}

	@Override
	public void startSession() throws BeachSessionException {
		
	}

	@Override
	public void stopSession() throws BeachSessionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		return entity.getId();
	}

	@Override
	public BeachSession getSession() throws BeachSessionException{
		if(session == null) { 
			throw new BeachSessionException("Beach Sesssion Not Instantiated");
		}
		return session;
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public BeachSystemModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
}
