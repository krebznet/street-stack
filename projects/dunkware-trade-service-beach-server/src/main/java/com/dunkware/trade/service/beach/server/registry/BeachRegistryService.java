package com.dunkware.trade.service.beach.server.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.sdk.core.model.system.SystemType;

@Service()
public class BeachRegistryService {
	
	@Autowired
	private ApplicationContext ac;;

	public System getSystem(SystemType type) throws Exception { 
		return null;
	}
}
