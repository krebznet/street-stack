package com.dunkware.trade.service.stream.server.controller.session.container.core;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainer;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerMarkers;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerService;
import com.dunkware.trade.service.stream.server.controller.session.container.anot.ASessionContainerExtension;

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
	
	// session container extensions 
	private Set<Class<?>> extensions = null;
	
	private Marker streamInfo = MarkerFactory.getMarker("StreamInfo");

	private boolean loaded = false; 
	/**
	 * Loads after the context has been loaded, we create a container for each stream found 
	 */
	@PostConstruct()
	public void load() { 
		if(loaded)return;
		
		Thread runner = new Thread() { 
			
			public void run() { 
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(logger.isInfoEnabled()) { 
					logger.info(streamInfo, "Starting Session Container Service");
				}
				if(controllerService.getStreams().size() == 0) { 
					logger.error(streamInfo, "No Streams Found From Controller, Pausing 5 Seconds");
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					if(controllerService.getStreams().size() == 0) { 
						logger.error(streamInfo, "No Streams Found From Controller Post 5 Second Pause, Sleeping 5 more seconds");
						try {
							Thread.sleep(5000);	
						} catch (Exception e) {
							// TODO: handle exception
						}
						if(controllerService.getStreams().size() == 0) { 
							logger.error(streamInfo, "No Streams Found Controller Post 10 Seconds WTF Duncan");
						} else { 
							logger.info(streamInfo, "Streams Found in Controller after Post 10 Seconds");
						}
						
						
						
					} else { 
						logger.info(streamInfo, "After Pause 5 second 1 or more streams returned");
					}
				}
				// lets load extension classes 
				extensions = cluster.getDunkwareReflections().getTypesAnnotatedWith(ASessionContainerExtension.class);
				
				for (StreamController stream : controllerService.getStreams()) {
					logger.info(streamInfo, "Creating Session Container For Stream " + stream.getName());;
					SessionContainerImpl container = new SessionContainerImpl();
					ac.getAutowireCapableBeanFactory().autowireBean(container);
					try {
						logger.info(streamInfo, "Starting Stream Session Container " + stream.getName());
						container.start(stream);
						logger.info("streamInfo, Started Stream Session Container " + stream.getName());
						containers.put(stream.getName(), container);
						logger.info(SessionContainerMarkers.containerMarker(), "Started Stream " + stream.getName() +  " Session Container");
					} catch (Exception e) {
						logger.error(streamInfo, "Exception Starting Stream Session Container " + e.toString());
						logger.error("Exception Starting Session Container " + e.toString());
					}
				}
				
				loaded = true;
							
			}
			
		}; 
		
		runner.start();
	
	}
	
	@Override
	public Set<Class<?>> getContainerExtensions() {
		return extensions;
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
