package com.dunkware.spring.cluster.core.controllers;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.message.DunkNetMessage;


public class DunkNetChannelController implements DunkNetController {

	private DunkNet net;

	@Override
	public boolean handleMessage(DunkNetMessage message) throws DunkNetException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(DunkNet net) {
		// TODO Auto-generated method stub
		
	}
	
	

	

}
