package com.dunkware.trade.service.stream.server.blueprint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.trade.service.stream.server.repository.StreamRepo;

@Service
public class StreamBlueprintService  {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String,StreamBlueprint> blueprints = new ConcurrentHashMap<String,StreamBlueprint>();
	
		
	@Autowired
	private StreamRepo streamRepo; 
		
	private DEventNode eventNode; 
	
	private DEventTree eventTree; 
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private ExecutorService executorService; 
	
	
	
	public DEventNode getEventNode() { 
		return eventNode; 
	}
	
	public StreamBlueprint getBlueprint(String streamIdent) throws Exception { 
		StreamBlueprint blueprint = blueprints.get(streamIdent);
		if(blueprint == null) { 
			throw new Exception("Stream Blueprint not found for " + streamIdent);
		}
		return blueprint;
	}

	@PostConstruct
	public void onApplicationEvent() {
		try {
			streamRepo.findAll();
			eventTree = DEventTree.newInstance(executorService.get());
			eventNode = eventTree.getRoot().createChild(this);
			for (StreamEntity ent : streamRepo.findAll()) {
				StreamBlueprint blueprint = new StreamBlueprint();
				ac.getAutowireCapableBeanFactory().autowireBean(blueprint);
				try {
					blueprint.init(ent, StreamBlueprintService.this);;
					blueprints.put(ent.getName(), blueprint);
				} catch (Exception e) {
					logger.error("Exception init stream blueprint " + e.toString(),e);
				}
			}		
			
		} catch (Exception e) {
			logger.error("this");
		}
		
	}
	
}
