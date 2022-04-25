package com.dunkware.net.cluster.server.core;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.logging.Markers;

@Service
public class ClusterService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private org.springframework.context.ApplicationContext ac;
	
	private ClusterNodeService nodeManger; 
	
	private ClusterEventManager eventManager; 

	@PostConstruct
	public void load() { 
		nodeManger = new ClusterNodeService();
		ac.getAutowireCapableBeanFactory().autowireBean(nodeManger);;
		try {
			nodeManger.start(this);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Crash"), "Node Manager Start Exception " + e.toString());
			System.exit(-1);
		}
		eventManager = new ClusterEventManager();
		ac.getAutowireCapableBeanFactory().autowireBean(eventManager);
		logger.info(Markers.serviceStart(), "Started Cluster Server Service");
		
	}
	
	public ClusterNodeService getNodeService() { 
		return nodeManger;
	}
	
	public ClusterEventManager getEventManager() { 
		return eventManager;
	}
}
