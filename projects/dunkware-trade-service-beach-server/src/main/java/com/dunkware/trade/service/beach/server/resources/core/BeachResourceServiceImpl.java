package com.dunkware.trade.service.beach.server.resources.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.service.beach.server.resources.BeachResourceService;
import com.dunkware.trade.service.beach.server.resources.BeachResourceSystem;
import com.dunkware.trade.service.beach.server.resources.entity.BeachResourceSystemDO;

@Service()
public class BeachResourceServiceImpl implements BeachResourceService {

	private List<BeachResourceSystem> systems = new ArrayList<BeachResourceSystem>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());	

	@Override
	public List<BeachResourceSystem> getSystems() {
		return systems;
	}
	
	@PostConstruct
	public void init() { 
		System.out.println("hello world");
	}

	@Override
	public void insertSystem(String name, SystemType type) throws Exception {
		// check if name already exists
		for (BeachResourceSystem sys : systems) {
			if (sys.getName().equalsIgnoreCase(name)) {
				throw new Exception("System Name " + name + " already exists");
			}
		}
		// try to serialize the system 
		String json = null; 
		try {
			json = DJson.serialize(type);
		} catch (Exception e) {
			logger.error("Exception serializing a system type on insert system " + e.toString());
			throw new Exception("Internal exception serializing system type " + e.toString());
		}
		
		BeachResourceSystemDO entity = new BeachResourceSystemDO();
		entity.setJson(json);
		entity.setName(name);
		
		try {
			// now lets insert it right? 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	@Override
	public BeachResourceSystem getSystem(long id) throws Exception {
		
		return null;
	}

	@Override
	public void saveSystem(long id, SystemType type) throws Exception {
		

	}

}
