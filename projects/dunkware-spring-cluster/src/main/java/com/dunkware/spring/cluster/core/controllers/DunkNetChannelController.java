package com.dunkware.spring.cluster.core.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.message.DunkNetMessage;


public class DunkNetChannelController implements DunkNetController {

	private DunkNet net;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("DunkNet");


	@Override
	public void init(DunkNet net) {
		this.net = net;
		
	}
	
	@Override
	public boolean handleMessage(DunkNetMessage message) throws DunkNetException {
		// TODO Auto-generated method stub
		return false;
	}


	
	

	

}
