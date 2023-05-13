package com.dunkware.trade.service.beach.server.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.trade.service.beach.server.common.BeachRuntime;

public class BeachContext {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeachRuntime runtime; 
	
	public void start(BeachPlay play) throws Exception { 
		
	}
	
	
}
