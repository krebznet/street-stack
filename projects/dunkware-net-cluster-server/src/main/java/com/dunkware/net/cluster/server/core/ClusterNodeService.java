package com.dunkware.net.cluster.server.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClusterNodeService {
	
	private List<ClusterNode> nodes = new ArrayList<ClusterNode>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void load() { 
		
	}

}
