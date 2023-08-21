package com.dunkware.spring.cluster.core.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.message.DunkNetMessage;


public class DunkNetPingController implements DunkNetController {

	private DunkNet dunkNet;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("DunkNet");

	@Autowired
	private ApplicationContext ac;

	public void init(DunkNet net) {
		this.dunkNet = net;
	}
	
	@Override
	public boolean handleMessage(DunkNetMessage message) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private class PingSender extends Thread { 
		
		public void run() {
			setName("DunkNet-PingSender");
			while(!interrupted()) { 
				
			}
		}
	}


	

}
