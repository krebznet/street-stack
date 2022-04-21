package com.dunkware.net.cluster.node.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.events.DClusterComponentAdded;

@Service
public class ClusterRegistry {

	private List<Object> registeredComponents = new ArrayList<Object>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ClusterImpl cluster;
	
	public void start(ClusterImpl cluster)   { 
		this.cluster = cluster; 
		
	}
	public void addComponent(Object component)  {
		registeredComponents.add(component);
		if(logger.isTraceEnabled()) { 
			logger.trace("Added Component Class " + component.getClass().getName());
		}
		cluster.getEventTree().getRoot().event(new DClusterComponentAdded(component));
		
	}
	
	
	public void removeComponent(Object component) { 
		
	}
	
	
}
