package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;

@Service()
public class SessionContainerServiceImpl implements SessionContainerService {

	
	@Autowired
	private Cluster cluster; 
	
	@Autowired
	private StreamControllerService controllerService;
	
	@Autowired
	private ApplicationContext ac; 
	
	private Map<String,SessionContainer> containers = new ConcurrentHashMap<String,SessionContainer>(); 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	/**
	 * Loads after the context has been loaded, we create a container for each stream found 
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void load() { 
		for (StreamController stream : controllerService.getStreams()) {
			SessionContainerImpl container = new SessionContainerImpl();
			ac.getAutowireCapableBeanFactory().autowireBean(container);
			try {
				container.start(stream);
				containers.put(stream.getName(), container);
			} catch (Exception e) {
				logger.error("Exception Starting Session Container " + e.toString());
			}
		}
		
	}
	
	
	@Override
	public SessionContainer getContainer(String streamIdentifier) throws Exception {
		SessionContainer container = containers.get(streamIdentifier);
		if(container == null) { 
			throw new Exception("Container " + streamIdentifier + " not found");
		}
		return container;
	}

}
