package com.dunkware.net.cluster.node.internal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClusterRegistry {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<ClusterExtension> extensions  = new ArrayList<ClusterExtension>();
	private List<String> serviceClasses = new ArrayList<String>();
	private ClusterImpl cluster;
	
	// might need to change this
	// so why did he hate technology, industrial society 
	// great intellect just a little bit off. 
	@PostConstruct
	public void start(ClusterImpl cluster)   { 
		this.cluster = cluster; 
			
	}

	public List<ClusterExtension> getExtensions() {
		return extensions;
	}
	
	public List<String> getServiceClasses() { 
		return serviceClasses;
	}
	
	
	
	
	
	
	
	
}
