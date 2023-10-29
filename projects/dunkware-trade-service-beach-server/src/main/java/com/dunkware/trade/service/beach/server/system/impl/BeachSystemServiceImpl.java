package com.dunkware.trade.service.beach.server.system.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.beach.model.system.BeachSystemModel;
import com.dunkware.trade.service.beach.server.broker.BeachBrokerService;
import com.dunkware.trade.service.beach.server.system.BeachSystem;
import com.dunkware.trade.service.beach.server.system.BeachSystemBean;
import com.dunkware.trade.service.beach.server.system.BeachSystemService;

import ca.odell.glazedlists.ObservableElementList;

@Service
public class BeachSystemServiceImpl implements BeachSystemService  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("BeachSystem");
	
	@Autowired
	private BeachBrokerService brokerService; 
	
	@Autowired
	private ApplicationContext ac; 
	
	
	private ObservableElementList<BeachSystemBean> beans;
	
	@PostConstruct
	private void init() { 
		// find systems 
		// then load the fuckers. 
	}
	
	@Override
	public Collection<BeachSystem> getSystems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableElementList<BeachSystemBean> getBeans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSystem(BeachSystemModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSystem(long systemId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startSystem(long systemId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSystem(long systemId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSystem(BeachSystemModel model, long systemId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
